package com.FlexPay.FlexPay.repository;

import com.FlexPay.FlexPay.entities.Transaction;
import com.FlexPay.FlexPay.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {


}
