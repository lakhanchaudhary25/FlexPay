package com.FlexPay.FlexPay.repository;

import com.FlexPay.FlexPay.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String number);

   public Optional<User> findByEmail(String email);
}
