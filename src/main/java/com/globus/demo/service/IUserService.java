package com.globus.demo.service;

import com.globus.demo.model.entites.User;
import com.globus.demo.response.token.Token;

public interface IUserService {
    Token create(User user);
    Token read(User user);
    User getUserInformation(Token token);
}
