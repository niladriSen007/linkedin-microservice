package com.niladri.connection_service.store;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        Long userId = UserContextHolder.getCurrentUserId();
        if (userId != null) {
            template.header("X-User-Id", userId.toString());
        }
    }
}
