package com.niladri.connection_service.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "connections")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;

    private Long connectionUserId;

    @Enumerated(EnumType.STRING)
    private ConnectionStatus connectionStatus=ConnectionStatus.PENDING;
}
