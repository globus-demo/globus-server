package com.globus.demo.repository;

import com.globus.demo.model.entites.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepo extends JpaRepository<Post, Long> {
    List<Post> findAllByUserEmail(String email);
}
