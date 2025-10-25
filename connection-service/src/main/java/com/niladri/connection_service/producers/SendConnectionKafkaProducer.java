package com.niladri.connection_service.producers;


import com.niladri.connection_service.events.SendConnectionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendConnectionKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishSendConnectionEvent(String topicName, Long eventId, SendConnectionEvent event) {
        kafkaTemplate.send(topicName, eventId.toString(), event)
                .whenComplete((result, err) -> {
                    if (err != null) {
                        System.out.println("Error publishing send connection event: " + err.getMessage());
                    }
                });

    }

}
