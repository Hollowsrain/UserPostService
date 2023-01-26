package com.epam.donatas.kubernetes.demo.service;

import com.epam.donatas.kubernetes.demo.model.Post;

public interface PostService {

    Post savePost(Post post);

    Post getPostById(Long id);

    void deletePostById(Long id);

    Post updatePost(Long id, Post post);
}
