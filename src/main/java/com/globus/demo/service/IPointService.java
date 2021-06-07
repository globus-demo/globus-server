package com.globus.demo.service;

import com.globus.demo.model.entites.Point;
import com.globus.demo.response.Response;

import java.util.List;

public interface IPointService {
    List<Point> getPointByEmail(String email);
    Response addNewPoint(Point point);
    Response deletePoint(Point point);
}
