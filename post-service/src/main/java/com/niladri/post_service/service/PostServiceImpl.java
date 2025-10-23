package com.niladri.post_service.service;

import com.niladri.post_service.dtos.PostRequestDto;
import com.niladri.post_service.dtos.PostResponseDto;
import com.niladri.post_service.mapper.ModelMapper;
import com.niladri.post_service.model.Posts;
import com.niladri.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService{
    private final PostRepository postRepository;

    @Override
    public PostResponseDto createPost(PostRequestDto postRequest) {
        Posts post = ModelMapper.mapToPost(postRequest);
        return null;
    }

    @Override
    public PostResponseDto getPostById(Long postId) {
        return null;
    }

    @Override
    public List<PostResponseDto> getAllPostsOfUser(Long userId) {
        return List.of();
    }
}
