package com.globus.demo.controllers;

import com.globus.demo.model.entites.Point;
import com.globus.demo.model.entites.User;
import com.globus.demo.response.Email;
import com.globus.demo.response.Response;
import com.globus.demo.service.IPointService;
import com.globus.demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PointController {

    private static Logger log = LoggerFactory.getLogger(UserController.class.getName());

    @Autowired
    private IPointService pointService;

    @PostMapping(value = "/addpoint")
    public ResponseEntity<?> addPoint(@RequestBody Point point) {
        Response response = pointService.addNewPoint(point);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/deletepoint")
    public ResponseEntity<?> deletePoint(@RequestBody Point point) {
        Response response = pointService.deletePoint(point);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/getpoints")
    public ResponseEntity<?> getPointsByEmail(@RequestBody Email e) {
        String email = e.getEmail();
        return new ResponseEntity<>(pointService.getPointByEmail(email), HttpStatus.OK);
    }
}
