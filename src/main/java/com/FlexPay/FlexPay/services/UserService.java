package com.FlexPay.FlexPay.services;

import com.FlexPay.FlexPay.dto.request.LoginRequest;
import com.FlexPay.FlexPay.dto.request.RegisterUserRequest;
import com.FlexPay.FlexPay.dto.response.LoginResponse;
import com.FlexPay.FlexPay.dto.response.RegisterUserResponse;


public interface UserService {


 RegisterUserResponse registerUser(RegisterUserRequest request);


LoginResponse loginUser(LoginRequest request);

}





