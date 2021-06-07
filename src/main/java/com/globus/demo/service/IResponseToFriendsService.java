package com.globus.demo.service;

import com.globus.demo.model.entites.User;
import com.globus.demo.response.Response;

import java.util.List;

public interface IResponseToFriendsService {
    Response add(String from, String to);
    Response delete(String from, String to);
    List<User> fromUser(String user);
    List<User> toUser(String user);
}
