package com.niladri.connection_service.mapper;

import com.niladri.connection_service.dtos.PersonRequest;
import com.niladri.connection_service.models.ConnectionStatus;
import com.niladri.connection_service.models.Person;

public class ModelMapper {

    public static Person mapToPerson(PersonRequest personRequest){
        return Person.builder()
                .name(personRequest.getName())
                .connectionUserId(personRequest.getConnectionUserId())
                .connectionStatus(ConnectionStatus.PENDING)
                .build();
    }

    public static PersonRequest mapToPersonRequest(Person person){
        return PersonRequest.builder()
                .name(person.getName())
                .connectionUserId(person.getConnectionUserId())
                .build();
    }
}
