package com.niladri.user_service.mapper;

import com.niladri.user_service.dtos.SignupRequestDto;
import com.niladri.user_service.dtos.UserResponseDto;
import com.niladri.user_service.model.User;

public class ModelMapper {

    public static UserResponseDto mapToUserResponseDto(User user) {
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
        return userResponseDto;
    }

    public static User mapToUser(SignupRequestDto signupRequestDto) {
        return User.builder()
                .name(signupRequestDto.getName())
                .email(signupRequestDto.getEmail())
                .password(signupRequestDto.getPassword())
                .build();
    }

}
