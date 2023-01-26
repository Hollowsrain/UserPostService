package com.epam.donatas.kubernetes.demo.repository;

import com.epam.donatas.kubernetes.demo.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
