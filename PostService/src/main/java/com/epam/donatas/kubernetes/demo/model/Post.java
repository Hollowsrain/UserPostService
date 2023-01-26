package com.epam.donatas.kubernetes.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "text")
    @Lob
    private String text;

    @Column(name = "posted_at")
    private ZonedDateTime postedAt;
}
