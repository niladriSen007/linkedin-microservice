package com.niladri.connection_service.controller;

import com.niladri.connection_service.dtos.PersonRequest;
import com.niladri.connection_service.dtos.UserResponse;
import com.niladri.connection_service.models.Person;
import com.niladri.connection_service.service.IConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class ConnectionController {

    private final IConnectionService connectionService;


    @PostMapping("/sendConnection")
    public ResponseEntity<Person> sendConnection(@RequestBody PersonRequest person) {
        log.info("Sending connection in connection controller: {}", person);
        return new ResponseEntity<>(connectionService.sendConnection(person), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponse>> getAllConnection() {
        log.info("Getting all connections in connection controller");
        return new ResponseEntity<>(connectionService.getAllConnection(), HttpStatus.OK);
    }

    @PutMapping("/acceptConnection/{connectionId}")
    public ResponseEntity<Person> acceptConnection(@PathVariable Long connectionId) {
        log.info("Accepting connection in connection controller: {}", connectionId);
        return new ResponseEntity<>(connectionService.acceptConnection(connectionId), HttpStatus.OK);
    }

    @DeleteMapping("/rejectConnection/{connectionId}")
    public ResponseEntity<Person> rejectConnection(@PathVariable Long connectionId) {
        log.info("Rejecting connection in connection controller: {}", connectionId);
        return new ResponseEntity<>(connectionService.rejectConnection(connectionId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteConnection/{connectionId}")
    public ResponseEntity<Person> deleteConnection(@PathVariable Long connectionId) {
        log.info("Deleting connection in connection controller: {}", connectionId);
        return new ResponseEntity<>(connectionService.deleteConnection(connectionId), HttpStatus.OK);
    }
}
