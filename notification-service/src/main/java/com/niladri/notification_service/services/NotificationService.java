package com.niladri.notification_service.services;

import com.niladri.notification_service.models.Notification;
import com.niladri.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(Long userId, String message) {
        log.info("Sending notification to userId: {} with message: {}", userId, message);
        Notification build = Notification.builder().userId(userId).message(message).build();
        notificationRepository.save(build);
        log.info("Notification saved: {}", build);
    }
}
