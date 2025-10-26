package com.niladri.post_service.producers;

import com.niladri.post_service.event.PostLikeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeKafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPostLikeEvent(String topicName, PostLikeEvent event) {
        kafkaTemplate.send(topicName,  event)
                .whenComplete((result, err) -> {
                    if (err != null) {
                        System.out.println("Error publishing post like event: " + err.getMessage());
                    }
                });

    }
}
