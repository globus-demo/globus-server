package com.globus.demo.service;

import com.globus.demo.model.User;

public interface IUserService {
    String create(User user);
    User read(String code);
}
