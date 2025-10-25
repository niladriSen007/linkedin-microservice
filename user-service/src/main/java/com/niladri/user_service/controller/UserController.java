package com.niladri.user_service.controller;

import com.niladri.user_service.dtos.LoginRequestDto;
import com.niladri.user_service.dtos.SignupRequestDto;
import com.niladri.user_service.dtos.UserResponseDto;
import com.niladri.user_service.service.IUserService;
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
public class UserController {

    private final IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {
        log.info("Signing up user: {}", signupRequestDto);
        UserResponseDto userDto = userService.signUp(signupRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<Boolean> getUserById(@PathVariable Long userId) {
        log.info("Getting user existence by id: {}", userId);
        Boolean isUserExist = userService.getUserById(userId);
        return new ResponseEntity<>(isUserExist, HttpStatus.OK);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<UserResponseDto>> batchUser(@RequestBody List<Long> userdIdList) {
        log.info("Batching users: {}", userdIdList);
        List<UserResponseDto> userDtoList = userService.batchUser(userdIdList);
        return new ResponseEntity<>(userDtoList, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("Logging in user: {}", loginRequestDto);
        String token = userService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }

}
