package com.niladri.post_service.service;

public interface IPostLikeService {
    void likePost(Long postId);
    void unlikePost(Long postId);
}
