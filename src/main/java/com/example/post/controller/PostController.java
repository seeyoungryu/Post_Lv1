package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    // 게시물 생성 API
    @PostMapping("")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
    }

    // 모든 게시물 조회 API
    @GetMapping("")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // 특정 게시물 조회 API
    @GetMapping("/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    // 게시물 수정 API
    @PutMapping("/{postId}")
    public ResponseEntity<Object> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto
    ) {
        try {
            PostResponseDto postResponseDto = postService.updatePost(postId, postRequestDto);
            return ResponseEntity.ok(postResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 게시물 삭제 API
    @DeleteMapping("/{postId}")
    public ResponseEntity<Object> deletePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto
    ) {
        try {
            postService.deletePost(postId, postRequestDto);
            return ResponseEntity.ok("삭제가 완료되었습니다");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 오류");
        }
    }
}
