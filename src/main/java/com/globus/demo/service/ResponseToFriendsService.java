package com.globus.demo.service;

import com.globus.demo.model.entites.ResponseToFriends;
import com.globus.demo.model.entites.User;
import com.globus.demo.repository.IResponseRepository;
import com.globus.demo.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseToFriendsService implements IResponseToFriendsService {
    private static Logger log = LoggerFactory.getLogger(ResponseToFriendsService.class.getName());

    @Autowired
    private IResponseRepository responseToFriendsService;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public boolean add(String from, String to) {
        ResponseToFriends response = responseToFriendsService.findByEmailUserFromAndEmailUserTo(from, to);
        if (response != null) {
            return false;
        }

        ResponseToFriends responseToFriends = new ResponseToFriends();
        responseToFriends.setEmailUserFrom(from);
        responseToFriends.setEmailUserTo(to);

        responseToFriendsService.save(responseToFriends);
        return true;
    }

    @Override
    public boolean delete(String from, String to) {
        ResponseToFriends response = responseToFriendsService.findByEmailUserFromAndEmailUserTo(from, to);
        if (response == null) {
            return false;
        }

        Long id = response.getId();
        responseToFriendsService.deleteById(id);
        return true;
    }

    @Override
    public List<User> fromUser(String user) {
        List<ResponseToFriends> responseToFriends = responseToFriendsService.findAllByEmailUserFrom(user);
        List<User> users = new ArrayList<>(responseToFriends.size());
        for (ResponseToFriends response : responseToFriends) {
            String email = response.getEmailUserTo();
            User user1 = userRepository.findUserByEmail(email);
            if (user1 != null){
                users.add(user1);
            }
        }
        return users;
    }

    @Override
    public List<User> toUser(String user) {
        List<ResponseToFriends> responseToFriends = responseToFriendsService.findAllByEmailUserTo(user);
        List<User> users = new ArrayList<>(responseToFriends.size());
        for (ResponseToFriends response : responseToFriends) {
            String email = response.getEmailUserFrom();
            User user1 = userRepository.findUserByEmail(email);
            if (user1 != null){
                users.add(user1);
            }
        }
        return users;
    }
}
