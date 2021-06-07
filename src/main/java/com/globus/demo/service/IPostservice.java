package com.globus.demo.service;

import com.globus.demo.model.entites.Post;
import com.globus.demo.response.Response;

import java.util.List;

public interface IPostservice {
    Response addPost(Post post);
    List<Post> userPost(String email);
}
