package com.niladri.connection_service.service;

import com.niladri.connection_service.dtos.PersonRequest;
import com.niladri.connection_service.dtos.UserResponse;
import com.niladri.connection_service.models.Person;

import java.util.List;

public interface IConnectionService {

    List<UserResponse> getAllConnection();

    Person sendConnection(PersonRequest person);

    Person acceptConnection(Long connectionId);

    Person rejectConnection(Long connectionId);

    Person deleteConnection(Long connectionId);
}
