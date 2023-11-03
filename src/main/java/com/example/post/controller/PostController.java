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
        // postRequestDto를 사용하여 새 게시물을 생성하고 반환
        return postService.createPost(postRequestDto);
    }

    // 모든 게시물 조회 API
    @GetMapping("")
    public List<PostResponseDto> getPosts() {
        // 모든 게시물을 가져와서 PostResponseDto로 매핑하여 리스트로 반환
        return postService.getPosts();
    }

    // 특정 게시물 조회 API
    @GetMapping("/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        // postId를 사용하여 특정 게시물을 가져와서 PostResponseDto로 반환
        return postService.getPost(postId);
    }

    // 게시물 수정 API
    @PutMapping("/{postId}")
    public ResponseEntity<Object> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto
    ) {
        try {
            // 게시물을 업데이트하고 업데이트된 결과를 반환
            PostResponseDto postResponseDto = postService.updatePost(postId, postRequestDto);
            return ResponseEntity.ok(postResponseDto);  // 성공 시 200 OK 응답
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());  // 실패 시 400 Bad Request 응답과 메시지
        }
    }

    // 게시물 삭제 API
    @DeleteMapping("/{postId}")
    public ResponseEntity<Object> deletePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto
    ) {
        try {
            // 게시물을 삭제하고 성공 메시지를 반환
            postService.deletePost(postId, postRequestDto);
            return ResponseEntity.ok("삭제가 완료되었습니다");  // 성공 시 200 OK 응답
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 오류");  // 실패 시 400 Bad Request 응답과 메시지
        }
    }
}
