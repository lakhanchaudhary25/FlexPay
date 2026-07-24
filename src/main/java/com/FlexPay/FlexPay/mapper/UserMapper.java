package com.FlexPay.FlexPay.mapper;

import com.FlexPay.FlexPay.dto.request.RegisterUserRequest;
import com.FlexPay.FlexPay.dto.response.LoginResponse;
import com.FlexPay.FlexPay.dto.response.RegisterUserResponse;
import com.FlexPay.FlexPay.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public User toEntity(RegisterUserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());

        return user;
    }

    public RegisterUserResponse toResponse(User user) {

        RegisterUserResponse response = new RegisterUserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setProfilePictureUrl(user.getProfilePictureUrl());

        return response;
    }

    public LoginResponse toLoginResponse(String message){
        LoginResponse loginResponse= new LoginResponse();
        loginResponse.setMessage(message);
         return loginResponse;
    }
}
