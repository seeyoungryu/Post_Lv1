package com.example.post.repository;

import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
}
