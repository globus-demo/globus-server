package com.globus.demo.service;

import com.globus.demo.model.User;
import com.globus.demo.token.Token;

public interface IUserService {
    Token create(User user);
    Token read(User user);
    User getUserInformation(Token token);
}
