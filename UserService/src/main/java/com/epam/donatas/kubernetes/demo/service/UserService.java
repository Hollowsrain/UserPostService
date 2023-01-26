package com.epam.donatas.kubernetes.demo.service;

import com.epam.donatas.kubernetes.demo.model.User;

public interface UserService {

    User getUserById(Long id);

    User saveUser(User user);

    void deleteUserById(Long id);

    User updateUserById(Long id, User updatedUser);

}