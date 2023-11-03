package com.example.post.repository;

import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 스프링 빈으로 등록되는 리포지토리 클래스를 나타내는 어노테이션
public interface PostRepository extends JpaRepository<Post, Long> {
    // JpaRepository를 상속받아 Post 엔티티와 관련된 CRUD 메서드를 자동으로 제공

    List<Post> findAllByOrderByModifiedAtDesc();
    // "modifiedAt" 열을 기준으로 내림차순으로 모든 게시물을 조회하는 메서드
}
