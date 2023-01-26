package com.epam.donatas.kubernetes.demo.repository;

import com.epam.donatas.kubernetes.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
