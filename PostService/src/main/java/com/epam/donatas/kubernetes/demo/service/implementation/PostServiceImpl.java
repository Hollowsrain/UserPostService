package com.epam.donatas.kubernetes.demo.service.implementation;

import com.epam.donatas.kubernetes.demo.exception.PostNotFoundException;
import com.epam.donatas.kubernetes.demo.exception.UserNotFoundException;
import com.epam.donatas.kubernetes.demo.model.Post;
import com.epam.donatas.kubernetes.demo.model.User;
import com.epam.donatas.kubernetes.demo.repository.PostRepository;
import com.epam.donatas.kubernetes.demo.service.PostService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final String BASE_URL = "http://user-service:8080/users/";

    public PostServiceImpl(PostRepository repository) {
        this.postRepository = repository;
    }


    @Override
    @Transactional
    public Post savePost(Post post) {
        increaseUsersPostCount(post.getAuthorId());
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (!post.isPresent()) {
            throw new PostNotFoundException("Post doesn't exist with given id: " + id);
        }
        return post.get();
    }

    @Override
    @Transactional
    public void deletePostById(Long id) {
        decreaseUsersPostCount(id);
        postRepository.deleteById(id);

    }

    @Override
    @Transactional
    public Post updatePost(Long id, Post newPost) {
        Optional<Post> oldPost = postRepository.findById(id);

        if (!oldPost.isPresent()) {
            throw new PostNotFoundException("Post doesn't exist with given id: " + id);
        }
        newPost.setId(oldPost.get().getId());
        return savePost(newPost);
    }

    private void decreaseUsersPostCount(Long id) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = BASE_URL + id;
        User user = getUserForPost(id);

        if (user.getAmountOfPosts() <= 0) {
            throw new PostNotFoundException("Posts can't be decreased since user doesn't have posts");
        }

        user.setAmountOfPosts(user.getAmountOfPosts() - 1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        restTemplate.delete(uri, entity);
    }

    private void increaseUsersPostCount(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = BASE_URL + id;
        User user = getUserForPost(id);

        if (user.getAmountOfPosts() <= -1) {
            try {
                throw new Exception("Corrupted post count for user");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        user.setAmountOfPosts(user.getAmountOfPosts() + 1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        restTemplate.put(uri, entity);
    }

    private User getUserForPost(Long id) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = BASE_URL + id;

        User user = restTemplate.getForObject(uri, User.class);

        if (user == null) {
            throw new UserNotFoundException("user with id " + id + "cannot be found");
        }

        return user;
    }
}
