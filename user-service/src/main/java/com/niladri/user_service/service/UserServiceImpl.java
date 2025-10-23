package com.niladri.user_service.service;

import com.niladri.user_service.dtos.LoginRequestDto;
import com.niladri.user_service.dtos.SignupRequestDto;
import com.niladri.user_service.dtos.UserResponseDto;
import com.niladri.user_service.exception.BadRequest;
import com.niladri.user_service.exception.ResourceNotFound;
import com.niladri.user_service.mapper.ModelMapper;
import com.niladri.user_service.model.User;
import com.niladri.user_service.repository.UserRepository;
import com.niladri.user_service.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public UserResponseDto signUp(SignupRequestDto signupRequestDto) {
        log.info("Signing up user: {}", signupRequestDto);
        boolean isUserExists = userRepository.existsByEmail(signupRequestDto.getEmail());
        if (isUserExists) throw new BadRequest("User already exists with email: " + signupRequestDto.getEmail());
        User newUser = ModelMapper.mapToUser(signupRequestDto);
        String hashedPassword = PasswordUtils.hashPassword(signupRequestDto.getPassword());
        newUser.setPassword(hashedPassword);
        User savedUser = userRepository.save(newUser);
        log.info("User signed up successfully: {}", savedUser);
        return ModelMapper.mapToUserResponseDto(savedUser);
    }


    @Override
    public String login(LoginRequestDto loginRequestDto) {
        log.info("Logging in user: {}", loginRequestDto);
        User isUserExists = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() ->
                new ResourceNotFound("User not found with email id: " + loginRequestDto.getEmail())
        );
        log.info("User found with email id: {}", isUserExists);
        boolean isPasswordMatched = PasswordUtils.comparePassword(loginRequestDto.getPassword(), isUserExists.getPassword());
        if (!isPasswordMatched) throw new BadRequest("Invalid password");
        log.info("User logged in successfully: {}", isUserExists);
        return jwtService.generateAccessToken(isUserExists);
    }
}
