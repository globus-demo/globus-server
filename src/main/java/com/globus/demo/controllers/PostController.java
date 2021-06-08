package com.globus.demo.controllers;

import com.globus.demo.controllers.helpers.PostWithPoint;
import com.globus.demo.model.entites.Point;
import com.globus.demo.model.entites.Post;
import com.globus.demo.response.Email;
import com.globus.demo.response.Response;
import com.globus.demo.service.IPointService;
import com.globus.demo.service.IPostservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PostController {

    private static Logger log = LoggerFactory.getLogger(PostController.class.getName());

    @Autowired
    private IPostservice postservice;

    @Autowired
    private IPointService pointService;

    @PostMapping(value = "/addpost")
    public ResponseEntity<?> addPoint(@RequestBody PostWithPoint post) {
        Post post1 = new Post();
        post1.setText(post.getText());
        post1.setUserEmail(post.getUserEmail());
        post1.setUserName(post.getUserName());
        post1.setUserSurname(post.getUserSurname());
        post1.setTime(post.getTime());
        post1.setTitle(post.getTitle());
        
        Point pointUser = post.getPoint();
        Long idPost;
        if (post.getPoint() != null) {
            List<Point> points = pointService.getPointByEmail(post.getUserEmail());

            List<Point> points1 = points.stream().filter(p ->
            {
                return p.getLongitude().equals(pointUser.getLongitude()) &&
                        p.getLatitude().equals(pointUser.getLatitude()) &&
                        p.getUserEmail().equals(pointUser.getUserEmail());
            }).collect(Collectors.toList());

            if (points1.size() == 0) {
                pointService.addNewPoint(pointUser);
                List<Point> pointsAA = pointService.getPointByEmail(pointUser.getUserEmail());
                List<Point> points2 = pointsAA.stream().filter(p ->
                {
                    return p.getLongitude().equals(pointUser.getLongitude()) &&
                            p.getLatitude().equals(pointUser.getLatitude()) &&
                            p.getUserEmail().equals(pointUser.getUserEmail());
                }).collect(Collectors.toList());
                idPost = points2.get(0).getId();
                log.info("Гребаная с " + idPost);
            } else {
                idPost = points1.get(0).getId();
                log.info("Гребаная с " + idPost);
            }
            post1.setPointId(idPost);
        } else {
            post1.setPointId(-1L);
        }
        Response response = postservice.addPost(post1);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/findallpost")
    public ResponseEntity<?> addPoint(@RequestBody Email email) {
        List<Post> posts = postservice.userPost(email.getEmail());
        List<Point> points = pointService.getPointByEmail(email.getEmail());
        for (Point gg: points){
            log.info("Есть говно " + gg.getId());
        }
        List<PostWithPoint> postWithPoints = new ArrayList<>();
        for (Post post : posts ) {
            PostWithPoint post1 = new PostWithPoint();
            post1.setText(post.getText());
            post1.setUserEmail(post.getUserEmail());
            post1.setUserName(post.getUserName());
            post1.setUserSurname(post.getUserSurname());
            post1.setTime(post.getTime());
            post1.setId(post.getId());
            post1.setTitle(post.getTitle());
            log.info("POST GET ID " + post.getPointId());
            Optional<Point> point = points.stream().filter(p2 -> p2.getId().equals(post.getPointId())).findFirst();
            if (point.isPresent()) {
                log.info("Найдено говно " + point.get().getId());
                post1.setPoint(point.get());
            } else {
                post1.setPoint(null);
            }

            postWithPoints.add(post1);
        }
        return new ResponseEntity<>(postWithPoints, HttpStatus.OK);
    }
}