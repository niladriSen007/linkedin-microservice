package com.niladri.notification_service.consumer;



import com.niladri.connection_service.event.AcceptConnectionEvent;
import com.niladri.connection_service.event.SendConnectionEvent;
import com.niladri.notification_service.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionServiceConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "send-connection-topic")
    public void handleSendConnectionRequest(SendConnectionEvent sendConnectionRequestEvent) {
        log.info("handle connections: handleSendConnectionRequest: {}", sendConnectionRequestEvent);
        String message =
                "You have received a connection request from user with id: %d" + sendConnectionRequestEvent.getSenderId();
        notificationService.send(sendConnectionRequestEvent.getReceiverId(), message);
    }

    @KafkaListener(topics = "accept-connection-topic")
    public void handleAcceptConnectionRequest(AcceptConnectionEvent acceptConnectionRequestEvent) {
        log.info("handle connections: handleAcceptConnectionRequest: {}", acceptConnectionRequestEvent);
        String message =
                "Your connection request has been accepted by the user with id: %d" + acceptConnectionRequestEvent.getReceiverId();
        notificationService.send(acceptConnectionRequestEvent.getSenderId(), message);
    }
}
