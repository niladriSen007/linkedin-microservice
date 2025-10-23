package com.niladri.post_service.controller;


import com.niladri.post_service.service.IPostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
@Slf4j
public class PostLikeController {

    private final IPostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<String> likePost(@PathVariable Long postId) {
        log.info("Liking post in post controller: {}", postId);
        postLikeService.likePost(postId);
        return ResponseEntity.ok("Post liked successfully");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> unlikePost(@PathVariable Long postId) {
        postLikeService.unlikePost(postId);
        return ResponseEntity.ok("Post unliked successfully");
    }
}
