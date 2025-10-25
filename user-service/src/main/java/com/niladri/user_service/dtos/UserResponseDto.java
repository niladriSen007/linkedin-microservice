package com.niladri.user_service.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserResponseDto implements Serializable {
    private Long id;
    private String name;
    private String email;
}
