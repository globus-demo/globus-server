package com.globus.demo.service;

import com.globus.demo.model.User;
import com.globus.demo.token.Token;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService implements IUserService{

    private static final Map<String, User> CLIENT_REPOSITORY_MAP = new HashMap<>();

    private static final List<String> EMAILS = new ArrayList<>();

    // Переменная для генерации ID user'а
    private static final AtomicInteger USER_ID_HOLDER = new AtomicInteger();

    @Override
    public Token create(User user) {
        String email = user.getEmail();
        if(EMAILS.contains(email)){
            return null;
        }

        final int userId = USER_ID_HOLDER.incrementAndGet();
        user.setId(userId);
        String code = userId + user.getName() + user.getSurname();
        Token token = new Token();
        token.setToken(code);
        user.setToken(token);

        CLIENT_REPOSITORY_MAP.put(code, user);
        EMAILS.add(email);
        return token;
    }

    @Override
    public User read(Token token) {
        String code = token.getToken();

        return CLIENT_REPOSITORY_MAP.get(code);
    }
}
