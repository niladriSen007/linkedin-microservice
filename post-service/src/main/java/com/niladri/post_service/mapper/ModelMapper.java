package com.niladri.post_service.mapper;

import com.niladri.post_service.dtos.PostRequestDto;
import com.niladri.post_service.dtos.PostResponseDto;
import com.niladri.post_service.model.Posts;

public class ModelMapper {
    public static PostResponseDto mapToPostResponseDto(Posts post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .content(post.getContent())
                .userId(post.getUserId())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static Posts mapToPost(PostRequestDto postRequest) {
        return Posts.builder()
                .content(postRequest.getContent())
                .build();
    }
}
