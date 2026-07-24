package com.FlexPay.FlexPay.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RegisterUserRequest {
    @NotBlank
   private String name;
    @NotBlank
    @Email
   private String email;
    @NotBlank
    @Pattern(regexp = "^[6-9]\\d{9}$")
   private String phoneNumber;
    @Size(min = 8)
    @NotBlank
   private String password;

}
