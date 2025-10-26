package com.niladri.notification_service.repository;

import com.niladri.notification_service.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
