package com.globus.demo.controllers;

import com.globus.demo.model.User;
import com.globus.demo.service.IUserService;
import com.globus.demo.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestBody User user){
        Token token = userService.create(user);
        if(token == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<User> read(@RequestBody Token token){
        System.out.println(token.getToken());

        final User user = userService.read(token);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
