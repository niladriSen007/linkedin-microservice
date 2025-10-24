package com.niladri.post_service.controller;

import com.niladri.post_service.dtos.PostRequestDto;
import com.niladri.post_service.dtos.PostResponseDto;
import com.niladri.post_service.service.IPostService;
import com.niladri.post_service.store.UserContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final IPostService postsService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postDto) {
        log.info("Creating post in post controller: {}", postDto);
        PostResponseDto createdPost = postsService.createPost(postDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        log.info("Getting post in post controller: {}", postId);
        PostResponseDto postDto = postsService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostResponseDto>> getAllPostsOfUser(@PathVariable Long userId) {
        List<PostResponseDto> posts = postsService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(posts);
    }

}
