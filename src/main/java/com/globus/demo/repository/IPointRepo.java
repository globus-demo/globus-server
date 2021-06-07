package com.globus.demo.repository;

import com.globus.demo.model.entites.Point;
import com.globus.demo.model.entites.ResponseToFriends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPointRepo extends JpaRepository<Point, Long> {
    List<Point> findAllByUserEmail(String userEmail);
}
