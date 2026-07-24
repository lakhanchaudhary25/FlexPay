package com.FlexPay.FlexPay.controller;

import com.FlexPay.FlexPay.dto.request.LoginRequest;
import com.FlexPay.FlexPay.dto.request.RegisterUserRequest;
import com.FlexPay.FlexPay.dto.response.LoginResponse;
import com.FlexPay.FlexPay.dto.response.RegisterUserResponse;
import com.FlexPay.FlexPay.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    public RegisterUserResponse registerUser(@Valid @RequestBody RegisterUserRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponse userLogin(@Valid @RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }
}
