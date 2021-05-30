package com.globus.demo.controllers;

import com.globus.demo.model.User;
import com.globus.demo.service.IUserService;
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
        String code = userService.create(user);
        return new ResponseEntity<>(code, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<User> read(@RequestBody String code){
        final User user = userService.read(code);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
