package com.niladri.post_service.clients;

import com.niladri.post_service.dtos.PersonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "connection-service", path = "/connections")
public interface ConnectionClient {

//    @GetMapping("/core/get")
//    List<PersonResponse> getConnections();

}
