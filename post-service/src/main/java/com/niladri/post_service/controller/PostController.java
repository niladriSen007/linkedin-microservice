package com.niladri.post_service.controller;

import com.niladri.post_service.dtos.PostRequestDto;
import com.niladri.post_service.dtos.PostResponseDto;
import com.niladri.post_service.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class PostController {

    private final IPostService postsService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postDto) {
        PostResponseDto createdPost = postsService.createPost(postDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto postDto = postsService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostResponseDto>> getAllPostsOfUser(@PathVariable Long userId) {
        List<PostResponseDto> posts = postsService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(posts);
    }

}
