package com.globus.demo.service;

import com.globus.demo.controllers.UserController;
import com.globus.demo.model.User;
import com.globus.demo.token.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService implements IUserService{

    private static Logger log = LoggerFactory.getLogger(IUserService.class.getName());

    private static final Map<String, User> CLIENT_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID user'а
    private static final AtomicInteger USER_ID_HOLDER = new AtomicInteger();

    @Override
    public Token create(User user) {
        String email = user.getEmail();
        log.info("Create user from email: " + email);
        if(CLIENT_REPOSITORY_MAP.containsKey(email)) {
            log.info("Create already exist from email: " + email);
            return null;
        }

        final int userId = USER_ID_HOLDER.incrementAndGet();
        user.setId(userId);
        String code = userId + user.getName() + user.getSurname();
        Token token = new Token();
        token.setToken(code);
        user.setToken(token);

        CLIENT_REPOSITORY_MAP.put(email, user);
        return token;
    }

    @Override
    public Token read(User user) {
        User userFromCollection = CLIENT_REPOSITORY_MAP.get(user.getEmail());
        if(userFromCollection != null) {
            if (userFromCollection.getPassword().equals(user.getPassword())) {
                return userFromCollection.getToken();
            }
            else {
                log.info("Password fake!");
                return null;
            }
        }

        log.info("User not found!");
        return null;
    }

    @Override
    public User getUserInformation(Token token1) {
        return CLIENT_REPOSITORY_MAP.values().stream().
                filter(x ->x.getToken().getToken().equals(token1.getToken())).
                findAny().
                get();
    }
}
