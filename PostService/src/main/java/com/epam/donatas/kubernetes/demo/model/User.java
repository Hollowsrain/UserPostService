package com.epam.donatas.kubernetes.demo.model;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String username;
    private int amountOfPosts;
}
