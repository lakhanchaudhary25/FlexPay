package com.FlexPay.FlexPay.repository;

import com.FlexPay.FlexPay.entities.Transaction;
import com.FlexPay.FlexPay.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    public List<Transaction> findBySenderWallet_IdOrReceiverWallet_IdOrderByCreatedAtDesc(Long senderId , Long ReceiverId);


}
