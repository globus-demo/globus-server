package com.globus.demo.service;

import com.globus.demo.model.User;
import com.globus.demo.token.Token;

public interface IUserService {
    Token create(User user);
    User read(Token token);
}
