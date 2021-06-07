package com.globus.demo.service;

import com.globus.demo.model.entites.Point;
import com.globus.demo.repository.IPointRepo;
import com.globus.demo.repository.IUserRepository;
import com.globus.demo.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PointService implements IPointService {

    private static Logger log = LoggerFactory.getLogger(PointService.class.getName());

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPointRepo pointRepository;


    @Override
    public List<Point> getPointByEmail(String email) {
        return pointRepository.findAllByUserEmail(email);
    }

    @Override
    public Response addNewPoint(Point point) {
        List<Point> points = pointRepository.findAllByUserEmail(point.getUserEmail());
        AtomicBoolean check = new AtomicBoolean(true);
        points.forEach(p -> {
            if (p.getLatitude().equals(point.getLatitude()) &&
                p.getLongitude().equals(point.getLongitude())) {
                check.set(false);
            }
        });
        if (check.get()) {
            pointRepository.save(point);
            return new Response(true, "Add");
        }
        return new Response(false, "this point already exist");
    }

    @Override
    public Response deletePoint(Point point) {
        List<Point> points = pointRepository.findAllByUserEmail(point.getUserEmail());
        AtomicBoolean check = new AtomicBoolean(false);
        points.forEach(p -> {
            if (p.getLatitude().equals(point.getLatitude()) &&
                    p.getLongitude().equals(point.getLongitude())) {
                check.set(true);
            }
        });
        if (check.get()) {
            pointRepository.delete(point);
            return new Response(true, "Delete");
        }
        return new Response(false, "this point not exist");
    }
}
