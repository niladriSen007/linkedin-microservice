package com.niladri.connection_service.dtos;

import com.niladri.connection_service.models.ConnectionStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonRequest {
    private String name;
    private Long connectionUserId;
}
