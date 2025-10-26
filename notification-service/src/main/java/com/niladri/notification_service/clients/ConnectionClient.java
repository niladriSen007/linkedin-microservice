package com.niladri.notification_service.clients;


import com.niladri.notification_service.dtos.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "connection-service",path = "/connections")
public interface ConnectionClient {

    @GetMapping("/core/getAll")
    List<UserResponse> getAllConnection(@RequestHeader("X-User-Id") Long userId);

}
