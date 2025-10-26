package com.niladri.connection_service.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendConnectionEvent {
    private Long senderId;
    private Long receiverId;
}
