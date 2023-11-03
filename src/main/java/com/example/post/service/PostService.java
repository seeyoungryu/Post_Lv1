package com.example.post.service;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import org.springframework.stereotype.Service;
import com.example.post.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service // 스프링 빈으로 등록되는 서비스 클래스를 나타내는 어노테이션
@RequiredArgsConstructor // 생성자 주입을 위한 Lombok 어노테이션
public class PostService {
    private final PostRepository postRepository; // Post 엔티티의 데이터를 다루는 데 사용

    // 게시물 생성
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        // 요청 DTO를 이용하여 게시물 엔티티 생성 후 저장
        Post post = new Post(postRequestDto);
        Post savedPost = postRepository.save(post);
        return new PostResponseDto(savedPost);
    }

    // 모든 게시물 조회
    public List<PostResponseDto> getPosts() {
        // 수정일자를 기준으로 내림차순으로 모든 게시물 조회 후 DTO로 변환하여 반환
        return postRepository.findAllByOrderByModifiedAtDesc()
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    // 특정 게시물 조회
    public PostResponseDto getPost(Long postId) {
        // 게시물 ID를 이용하여 특정 게시물 조회 후 DTO로 변환하여 반환
        Post post = findPost(postId);
        return new PostResponseDto(post);
    }

    // 게시물 수정
    @Transactional // 트랜잭션을 지원하기 위한 어노테이션
    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {
        // 게시물 ID를 이용하여 특정 게시물 조회 후 업데이트 및 DTO로 변환하여 반환
        Post post = findPost(postId);
        validatePassword(post, postRequestDto.getPassword());
        post.update(postRequestDto);
        return new PostResponseDto(post);
    }

    // 게시물 삭제
    public void deletePost(Long postId, PostRequestDto postRequestDto) {
        // 게시물 ID를 이용하여 특정 게시물 조회 후 삭제
        Post post = findPost(postId);
        validatePassword(post, postRequestDto.getPassword());
        postRepository.delete(post);
    }

    // 게시물 조회 및 예외 처리
    private Post findPost(Long postId) {
        // 게시물 ID를 이용하여 특정 게시물 조회, 존재하지 않으면 예외 발생
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
    }

    // 비밀번호 검증
    private void validatePassword(Post post, String password) {
        // 게시물의 비밀번호와 입력된 비밀번호 비교하여 일치하지 않으면 예외 발생
        if (!post.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
    }
}
