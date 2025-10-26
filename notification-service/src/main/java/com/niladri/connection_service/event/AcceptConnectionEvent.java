package com.niladri.connection_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcceptConnectionEvent implements Serializable {
    private Long senderId;
    private Long receiverId;
}
