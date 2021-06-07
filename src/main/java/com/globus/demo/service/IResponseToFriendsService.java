package com.globus.demo.service;

import com.globus.demo.model.entites.User;

import java.util.List;

public interface IResponseToFriendsService {
    boolean add(String from, String to);
    boolean delete(String from, String to);
    List<User> fromUser(String user);
    List<User> toUser(String user);
}
