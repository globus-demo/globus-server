package com.globus.demo.service;

import com.globus.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService implements IUserService{

    private static final Map<String, User> CLIENT_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID user'а
    private static final AtomicInteger USER_ID_HOLDER = new AtomicInteger();

    @Override
    public String create(User user) {
        final int userId = USER_ID_HOLDER.incrementAndGet();
        user.setId(userId);
        String code = userId + user.getName() + user.getSurname();
        user.setCode(code);
        CLIENT_REPOSITORY_MAP.put(code, user);
        return code;
    }

    @Override
    public User read(String code) {
        return CLIENT_REPOSITORY_MAP.get(code);
    }
}
