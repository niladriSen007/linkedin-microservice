package com.niladri.post_service.service;

import com.niladri.post_service.event.PostLikeEvent;
import com.niladri.post_service.exception.BadRequest;
import com.niladri.post_service.exception.ResourceNotFound;
import com.niladri.post_service.model.PostLike;
import com.niladri.post_service.model.Posts;
import com.niladri.post_service.producers.PostLikeKafkaProducer;
import com.niladri.post_service.repository.PostLikeRepository;
import com.niladri.post_service.repository.PostRepository;
import com.niladri.post_service.store.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeServiceImpl implements IPostLikeService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    private final PostLikeKafkaProducer postLikeKafkaProducer;

    @Override
    public void likePost(Long postId) {
        log.info("Liking post in post service: {}", postId);
        Posts post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post with id {} not available" + postId)
        );

        Long currentUserId = UserContextHolder.getCurrentUserId();

        boolean isPostAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(currentUserId, postId);
        if (isPostAlreadyLiked) {
            log.info("Post already liked");
            throw new BadRequest("Cannot like the same post again.");
        }
        PostLike postLike = PostLike.builder().userId(currentUserId).postId(postId).build();
        postLikeRepository.save(postLike);


        PostLikeEvent postLikeEvent = PostLikeEvent.builder()
                .postId(postId)
                .creatorId(post.getUserId())
                .likedByUserId(currentUserId)
                .build();

        postLikeKafkaProducer.publishPostLikeEvent("post-like-topic", postId, postLikeEvent);


        log.info("Post liked successfully: {}", postLike);

    }

    @Override
    public void unlikePost(Long postId) {
        log.info("Attempting to unlike the post with id: {}", postId);
        boolean isPostExists = postRepository.existsById(postId);
        if (!isPostExists) throw new ResourceNotFound("Resource not found with id: \"+postId");
        boolean isPostAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(1L, postId);
        if (!isPostAlreadyLiked) throw new BadRequest("Cannot unlike the post which is not liked.");
        postLikeRepository.deleteByUserIdAndPostId(1L, postId);
        log.info("Post unliked successfully: {}", postId);
    }
}
