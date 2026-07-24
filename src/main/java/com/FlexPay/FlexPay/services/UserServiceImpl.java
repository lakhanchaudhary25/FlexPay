package com.FlexPay.FlexPay.services;

import com.FlexPay.FlexPay.dto.request.LoginRequest;
import com.FlexPay.FlexPay.dto.request.RegisterUserRequest;
import com.FlexPay.FlexPay.dto.response.LoginResponse;
import com.FlexPay.FlexPay.dto.response.RegisterUserResponse;
import com.FlexPay.FlexPay.entities.User;
import com.FlexPay.FlexPay.entities.Wallet;
import com.FlexPay.FlexPay.exception.EmailAlreadyExistsException;
import com.FlexPay.FlexPay.exception.PhoneNumberAlreadyExists;
import com.FlexPay.FlexPay.exception.InvalidLoginCredentialException;
import com.FlexPay.FlexPay.mapper.UserMapper;
import com.FlexPay.FlexPay.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


//****** Dependency Injection in my FlexPay using Constructor injection ************************//
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserMapper userMapper,
                           UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    @Override
    public LoginResponse loginUser(LoginRequest request){
        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());
        if(optionalUser.isEmpty()) {
            throw new InvalidLoginCredentialException("Invalid Email or Password");
        }
        User user = optionalUser.get();
        if(!passwordEncoder.matches(request.getPassword(),user.getHashedPassword())){
            throw new InvalidLoginCredentialException("Invalid Email or Password");
        }
        return userMapper.toLoginResponse("Logged In Successfully");

    }
    @Transactional
    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");

        }
        if(userRepository.existsByPhoneNumber(request.getPhoneNumber())){
            throw new PhoneNumberAlreadyExists("Phone number already exists.");
        }
        User user = userMapper.toEntity(request);

        user.setHashedPassword(
                passwordEncoder.encode(request.getPassword())
        );
        Wallet wallet =new Wallet();
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setUser(user);
        user.setWallet(wallet);
        User savedUser= userRepository.save
                (user);

              return  userMapper.toResponse(savedUser);



    }
}
