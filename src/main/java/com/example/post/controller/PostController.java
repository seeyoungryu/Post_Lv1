package com.example.post.controller;

import com.example.post.dto.PostResponseDto;
import com.sparta.post.dto.postRequestDto;
import com.sparta.post.dto.postResponseDto;
import com.sparta.post.service.postService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final postService postService;

    //선언
    public PostController(PostService postService) {
        this.postService = postService;
    }    //생성자 - 스프링이 메모서비스를 가져와서 생성자로 주입 (방식)


    //-------------------------------
    @PostMapping("/posts")
    public postResponseDto createpost(@RequestBody postRequestDto requestDto) {
        return postService.createpost(requestDto);
    }

    @GetMapping("/posts")
    public List<postResponseDto> getposts() {
        return postService.getposts();
    }

//    @GetMapping("/posts")
//    public List<postResponseDto> getposts() {
//        return postService.getposts();
//    }

    @GetMapping("/all-posts")
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }


    @PutMapping("/posts/{id}")
    public Long updatepost(@PathVariable Long id, @RequestBody postRequestDto requestDto) {
        return postService.updatepost(id, requestDto);
    }

    @DeleteMapping("/posts/{id}")
    public Long deletepost(@PathVariable Long id) {
        return postService.deletepost(id);
    }
}

