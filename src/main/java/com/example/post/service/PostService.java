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

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 게시물 생성
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        Post savedPost = postRepository.save(post);
        return new PostResponseDto(savedPost);
    }

    // 모든 게시물 조회
    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc()
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    // 특정 게시물 조회
    public PostResponseDto getPost(Long postId) {
        Post post = findPost(postId);
        return new PostResponseDto(post);
    }

    // 게시물 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = findPost(postId);
        validatePassword(post, postRequestDto.getPassword());
        post.update(postRequestDto);
        return new PostResponseDto(post);
    }

    // 게시물 삭제
    public void deletePost(Long postId, PostRequestDto postRequestDto) {
        Post post = findPost(postId);
        validatePassword(post, postRequestDto.getPassword());
        postRepository.delete(post);
    }

    // 게시물 조회 및 예외 처리
    private Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
    }

    // 비밀번호 검증
    private void validatePassword(Post post, String password) {
        if (!post.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
    }
}
