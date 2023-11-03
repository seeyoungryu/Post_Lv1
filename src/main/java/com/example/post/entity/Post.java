package com.example.post.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    private Long id;
    private String username;
    private String contents;
    private String password;
    private String title;

    public LocalDateTime getCreatedAt() {
        return null;
    }

    public LocalDateTime getModifiedAt() {
        return null;
    }
}