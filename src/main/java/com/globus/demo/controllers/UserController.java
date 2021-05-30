package com.globus.demo.controllers;

import com.globus.demo.model.User;
import com.globus.demo.response.Response;
import com.globus.demo.service.IUserService;
import com.globus.demo.token.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class.getName());

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<?> create(@RequestBody User user) {
        log.info("Create new user" + user.getName() + " " + user.getSurname());
        Token token = userService.create(user);
        if (token == null) {
            log.info("User already exists");
            String text = "User already exists";
            Response response = new Response(false, text);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Response response = new Response(true, token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/getuser")
    public ResponseEntity<Response> getUserToken(@RequestBody User user) {
        log.info("Read user");
        final Token token = userService.read(user);
        if (token != null) {
            return new ResponseEntity<>(new Response(true, token), HttpStatus.OK);
        } else {
            log.info("User not found or password isn't right");
            return new ResponseEntity<>(new Response(false, "User not found or password isn't right"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/userinfo")
    public ResponseEntity<Response> read(@RequestBody Token token) {
        final User user = userService.getUserInformation(token);
        if (user != null) {
            return new ResponseEntity<>(new Response(true, user), HttpStatus.OK);
        } else {
            log.info("User not found");
            return new ResponseEntity<>(new Response(false, "User not found"), HttpStatus.NOT_FOUND);
        }
    }

}
