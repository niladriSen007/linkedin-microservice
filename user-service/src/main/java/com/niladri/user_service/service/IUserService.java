package com.niladri.user_service.service;

import com.niladri.user_service.dtos.LoginRequestDto;
import com.niladri.user_service.dtos.SignupRequestDto;
import com.niladri.user_service.dtos.UserResponseDto;

import java.util.List;

public interface IUserService {

    UserResponseDto signUp(SignupRequestDto signupRequestDto);
    String login(LoginRequestDto loginRequestDto);
    List<UserResponseDto> batchUser(List<Long> userdIdList);
    Boolean getUserById(Long userId);
}
