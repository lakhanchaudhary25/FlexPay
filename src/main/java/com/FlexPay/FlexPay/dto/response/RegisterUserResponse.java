package com.FlexPay.FlexPay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterUserResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String profilePictureUrl;
}
