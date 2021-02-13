package com.example.bojta_mk.service.impl;

import com.example.bojta_mk.model.Post;
import com.example.bojta_mk.repository.PostRepository;
import com.example.bojta_mk.service.PostService;
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

    @Override
    public Post delete(Long id) {
        Post post = findPost(id);
        postRepository.delete(post);
        return post;
    }

    @Override
    public Post findPost(Long id) {
        return postRepository.findById(id).get();
    }
}
