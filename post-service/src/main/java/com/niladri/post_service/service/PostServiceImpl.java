package com.niladri.post_service.service;

import com.niladri.post_service.dtos.PostRequestDto;
import com.niladri.post_service.dtos.PostResponseDto;
import com.niladri.post_service.exception.ResourceNotFound;
import com.niladri.post_service.mapper.ModelMapper;
import com.niladri.post_service.model.Posts;
import com.niladri.post_service.repository.PostRepository;
import com.niladri.post_service.store.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements IPostService {
    private final PostRepository postRepository;

    @Override
    public PostResponseDto createPost(PostRequestDto postRequest) {
        log.info("Creating post in post service: {}", postRequest);
        Long userId = UserContextHolder.getCurrentUserId();
        Posts post = ModelMapper.mapToPost(postRequest);
        post.setUserId(userId);
        Posts savedPost = postRepository.save(post);
        log.info("Post created successfully: {}", savedPost);
        return ModelMapper.mapToPostResponseDto(savedPost);
    }

    @Override
    public PostResponseDto getPostById(Long postId) {
        log.info("Getting post in post service: {}", postId);
        Posts post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post with id {} not available" + postId)
        );
        return ModelMapper.mapToPostResponseDto(post);
    }

    @Override
    public List<PostResponseDto> getAllPostsOfUser(Long userId) {
        log.info("Getting all posts of user in post service: {}", userId);
        List<Posts> posts = postRepository.findAllByUserId(userId);
        return posts.stream().map(ModelMapper::mapToPostResponseDto).toList();
    }
}
