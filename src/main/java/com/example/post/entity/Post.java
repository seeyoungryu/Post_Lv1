package com.example.post.entity;

import com.example.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // JPA 엔티티임을 나타내는 어노테이션
//@Table(name = "post") // "post" 테이블과 연결됨을 명시
@Getter // Getter 메서드 자동 생성을 위한 Lombok 어노테이션
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자 생성
@NoArgsConstructor // 기본 생성자 생성
public class Post extends Timestamped {
    @Id // 이 필드는 식별자(primary key) 역할을 한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성되는 식별자(strategy = GenerationType.IDENTITY)
    private Long id; // 게시물의 고유 식별자

    @Column // 이 필드는 데이터베이스의 열(column)에 매핑됨을 나타냄
    private String title; // 게시물 제목

    @Column
    private String password; // 게시물 비밀번호

    @Column
    private String username; // 게시물 작성자 이름

    @Column
    private String contents; // 게시물 내용

    // 생성자: PostRequestDto를 기반으로 엔티티 생성
    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.username = postRequestDto.getUsername();
        this.contents = postRequestDto.getContents();
        this.password = postRequestDto.getPassword();
    }

    // 엔티티를 업데이트하는 메서드
    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.username = postRequestDto.getUsername();
        this.contents = postRequestDto.getContents();
    }
}
