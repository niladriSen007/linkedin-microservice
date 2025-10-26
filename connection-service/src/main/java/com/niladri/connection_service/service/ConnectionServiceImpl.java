package com.niladri.connection_service.service;

import com.niladri.connection_service.clients.UserClient;
import com.niladri.connection_service.dtos.PersonRequest;
import com.niladri.connection_service.dtos.UserResponse;
import com.niladri.connection_service.event.AcceptConnectionEvent;
import com.niladri.connection_service.event.SendConnectionEvent;
import com.niladri.connection_service.exception.ResourceNotFound;
import com.niladri.connection_service.mapper.ModelMapper;
import com.niladri.connection_service.models.ConnectionStatus;
import com.niladri.connection_service.models.Person;
import com.niladri.connection_service.producers.AcceptConnectionKafkaProducer;
import com.niladri.connection_service.producers.SendConnectionKafkaProducer;
import com.niladri.connection_service.repository.ConnectionRepository;
import com.niladri.connection_service.store.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionServiceImpl implements IConnectionService {

    private final ConnectionRepository connectionRepository;
    private final UserClient userClient;
    private final AcceptConnectionKafkaProducer acceptConnectionKafkaProducer;
    private final SendConnectionKafkaProducer sendConnectionKafkaProducer;

    @Override
    public List<UserResponse> getAllConnection() {
        log.info("Getting all connections in connection service");
        Long userId = UserContextHolder.getCurrentUserId();
        List<Long> allConnectionOfUser = connectionRepository.findAllConnectionOfUser(userId);
        List<UserResponse> users = userClient.batchUser(allConnectionOfUser);
        log.info("Getting all connections in connection service");
        return users;
    }

    @Override
    public Person sendConnection(PersonRequest person) {
        log.info("Sending connection in connection service");
        Long userId = UserContextHolder.getCurrentUserId();
        log.info("User id: " + userId);
        Boolean isUserExists = userClient.isUserExists(person.getConnectionUserId());
        if (!isUserExists) {
            log.info("User not found with id: " + person.getConnectionUserId());
            throw new IllegalArgumentException("User not found with id: " + person.getConnectionUserId());
        }
        if (userId.equals(person.getConnectionUserId())) {
            log.info("User cannot connect to himself");
            throw new IllegalArgumentException("User cannot connect to himself");
        }
        boolean isUserConnected = connectionRepository.existsByUserIdAndConnectionUserId(userId, person.getConnectionUserId());
        if (isUserConnected) {
            log.info("User already connected");
            throw new IllegalArgumentException("User already connected");
        }
        Person newPerson = ModelMapper.mapToPerson(person);
        newPerson.setUserId(userId);
        log.info("Sending connection in connection service");
        Person savedConn = connectionRepository.save(newPerson);

        SendConnectionEvent sendConnectionEvent = SendConnectionEvent.builder()
                .senderId(userId)
                .receiverId(person.getConnectionUserId())
                .build();

        sendConnectionKafkaProducer.publishSendConnectionEvent("send-connection-topic", sendConnectionEvent);
        return savedConn;
    }

    @Override
    public Person acceptConnection(Long connectionId) {

        log.info("Accepting connection in connection service");
        Person personConnection = connectionRepository.findById(connectionId).orElseThrow(
                () -> new ResourceNotFound("Connection not found with id: " + connectionId));
        personConnection.setConnectionStatus(ConnectionStatus.ACCEPTED);
        log.info("Accepting connection in connection service");

        Person acceptedConnection = connectionRepository.save(personConnection);

        Long currentUserId = UserContextHolder.getCurrentUserId();

        AcceptConnectionEvent acceptConnectionEvent = AcceptConnectionEvent.builder()
                .senderId(personConnection.getUserId())
                .receiverId(currentUserId)
                .build();

        acceptConnectionKafkaProducer.publishAcceptConnectionEvent(
                "accept-connection-topic",
                acceptConnectionEvent);

        return acceptedConnection;
    }

    @Override
    public Person rejectConnection(Long connectionId) {
        log.info("Rejecting connection in connection service");
        Person personConnection = connectionRepository.findById(connectionId).orElseThrow(
                () -> new ResourceNotFound("Connection not found with id: " + connectionId));
        personConnection.setConnectionStatus(ConnectionStatus.REJECTED);
        log.info("Rejecting connection in connection service");
        return connectionRepository.save(personConnection);
    }

    @Override
    public Person deleteConnection(Long connectionId) {
        log.info("Deleting connection in connection service");
        Person personConnection = connectionRepository.findById(connectionId).orElseThrow(
                () -> new ResourceNotFound("Connection not found with id: " + connectionId));
        connectionRepository.delete(personConnection);
        log.info("Deleting connection in connection service");
        return personConnection;
    }
}
