package com.example.bojta_mk.service;

import com.example.bojta_mk.model.Post;

import java.util.List;

public interface PostService {
    List<Post> listAll();
    Post create(String headline, String description);
    Post delete(Long id);
    Post findPost(Long id);
}
