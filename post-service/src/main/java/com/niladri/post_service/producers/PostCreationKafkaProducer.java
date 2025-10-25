package com.niladri.post_service.producers;

import com.niladri.post_service.config.KafkaTopicConfig;
import com.niladri.post_service.event.PostCreationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCreationKafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPostCreationEvent(String topicName,PostCreationEvent event) {
        kafkaTemplate.send(topicName, event)
                .whenComplete((result, err) -> {
                    if (err != null) {
                        System.out.println("Error publishing post creation event: " + err.getMessage());
                    }
                });

    }
}
