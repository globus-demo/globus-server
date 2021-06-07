package com.globus.demo.service;

import com.globus.demo.model.entites.Post;
import com.globus.demo.repository.IPostRepo;
import com.globus.demo.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostservice {

    @Autowired
    private IPostRepo postRepo;

    @Override
    public Response addPost(Post post) {
        postRepo.save(post);
        return new Response(true, "Надеюсь что добавлен(");
    }

    @Override
    public List<Post> userPost(String email) {
        return postRepo.findAllByUserEmail(email);
    }
}
