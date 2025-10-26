package com.niladri.notification_service.consumer;


import com.niladri.notification_service.clients.ConnectionClient;
import com.niladri.notification_service.dtos.UserResponse;

import com.niladri.notification_service.services.NotificationService;
import com.niladri.post_service.event.PostCreationEvent;
import com.niladri.post_service.event.PostLikeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceConsumer {

    private final ConnectionClient connectionClient;
    private final NotificationService notificationService;


    @KafkaListener(topics = "post-creation-topic")
    public void handlePostCreatedEvent(PostCreationEvent postCreationEvent) {

        log.info("Sending notifications: handlePostCreatedEvent: {}", postCreationEvent);

        List<UserResponse> allConnection = connectionClient.getAllConnection(postCreationEvent.getCreatorId());

        for (UserResponse eachConnection : allConnection) {
            log.info("Notifying connection: {}", eachConnection);
            notificationService.send(eachConnection.getId(),
                    "New post created by userId: " + postCreationEvent.getCreatorId() +
                            " check it out ");
        }
    }

    @KafkaListener(topics = "post-like-topic")
    public void handlePostLikeEvent(PostLikeEvent postLikeEvent) {

        log.info("Sending notifications: handlePostLikeEvent: {}", postLikeEvent);

        log.info("Notifying post creator: {}", postLikeEvent.getCreatorId());

        notificationService.send(
                postLikeEvent.getCreatorId(),
                "Your post," + postLikeEvent.getPostId() + "has been liked by " +
                        postLikeEvent.getLikedByUserId());

    }
}
