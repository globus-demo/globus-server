package com.globus.demo.controllers;

import com.globus.demo.model.User;
import com.globus.demo.response.Response;
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
            String text = "User already exists";
            Response response = new Response(false, text);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Response response = new Response(true, token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<Response> read(@RequestBody Token token){
        final User user = userService.read(token);

        return user != null
                ? new ResponseEntity<>(new Response(true, user), HttpStatus.OK)
                : new ResponseEntity<>(new Response(false, "Not found"), HttpStatus.NOT_FOUND);
    }

}
