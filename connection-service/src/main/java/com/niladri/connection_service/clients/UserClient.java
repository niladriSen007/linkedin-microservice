package com.niladri.connection_service.clients;

import com.niladri.connection_service.dtos.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "user-service",path = "/users")
public interface UserClient {

    @PostMapping("/core/batch")
    List<UserResponse> batchUser(List<Long> allConnectionOfUser);

    @GetMapping("/core/{userId}")
    Boolean isUserExists(@PathVariable Long userId);
}
