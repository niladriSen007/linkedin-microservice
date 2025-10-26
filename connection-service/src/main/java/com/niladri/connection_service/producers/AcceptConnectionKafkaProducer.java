package com.niladri.connection_service.producers;

import com.niladri.connection_service.event.AcceptConnectionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AcceptConnectionKafkaProducer {


    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishAcceptConnectionEvent(String topicName, AcceptConnectionEvent event) {
        kafkaTemplate.send(topicName, event)
                .whenComplete((result, err) -> {
                    if (err != null) {
                        System.out.println("Error publishing accept connection event: " + err.getMessage());
                    }
                });

    }

}
