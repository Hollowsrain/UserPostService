package com.epam.donatas.kubernetes.demo.controller;

import com.epam.donatas.kubernetes.demo.model.Post;
import com.epam.donatas.kubernetes.demo.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        post.setPostedAt(ZonedDateTime.now());
        Post createdPost = postService.savePost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePostById(@Valid @RequestBody Post post, @PathVariable Long id) {
        post.setPostedAt(ZonedDateTime.now());
        Post updatedPost = postService.updatePost(id, post);
        return new  ResponseEntity<>(post, HttpStatus.OK);
    }
}
