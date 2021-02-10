package com.example.bojta_mk.service;

import com.example.bojta_mk.model.Post;
import com.example.bojta_mk.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> listAll() {
        return postRepository.findAll();
    }

    @Override
    public Post create(String headline, String description) {
        return postRepository.save(new Post(headline,description));
    }
}
