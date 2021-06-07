package com.globus.demo.controllers;

import com.globus.demo.model.entites.ResponseToFriends;
import com.globus.demo.model.entites.User;
import com.globus.demo.response.Email;
import com.globus.demo.response.Response;
import com.globus.demo.response.token.Token;
import com.globus.demo.service.IResponseToFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class ResponseToFriendsController {

    @Autowired
    IResponseToFriendsService responseToFriendsService;

    @PostMapping(value = "/follow")
    public ResponseEntity<?> follow(@RequestBody ResponseToFriends response) {
        Response answer = responseToFriendsService.add(response.getEmailUserFrom(),
                response.getEmailUserTo());
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PostMapping(value = "/followersMe")
    public ResponseEntity<?> followersMe(@RequestBody Email email) {
        List<User> users = responseToFriendsService.toUser(email.getEmail());
        List<User> answer = new ArrayList<>(users);
        answer.forEach(user -> user.setPassword(null));

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PostMapping(value = "/followersFromMe")
    public ResponseEntity<?> followersFromMe(@RequestBody Email email) {
        List<User> users = responseToFriendsService.fromUser(email.getEmail());
        List<User> answer = new ArrayList<>(users);
        answer.forEach(user -> user.setPassword(null));

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PostMapping(value = "/deleteFollowers")
    public ResponseEntity<?> delete(@RequestBody ResponseToFriends response) {
        Response answer = responseToFriendsService.delete(response.getEmailUserFrom(),
                response.getEmailUserTo());
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
