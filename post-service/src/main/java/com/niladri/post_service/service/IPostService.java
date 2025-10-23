package com.niladri.post_service.service;

import com.niladri.post_service.dtos.PostRequestDto;
import com.niladri.post_service.dtos.PostResponseDto;

import java.util.List;

public interface IPostService {
    PostResponseDto createPost(PostRequestDto postRequest);

    PostResponseDto getPostById(Long postId);

    List<PostResponseDto> getAllPostsOfUser(Long userId);
}
