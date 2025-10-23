package com.niladri.post_service.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostResponseDto {
    private Long id;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;
}
