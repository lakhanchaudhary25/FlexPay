package com.FlexPay.FlexPay.mapper;

import com.FlexPay.FlexPay.enums.Status;
import com.FlexPay.FlexPay.enums.TransactionType;
import com.FlexPay.FlexPay.dto.request.TransferRequest;
import com.FlexPay.FlexPay.dto.response.*;
import com.FlexPay.FlexPay.entities.Transaction;
import com.FlexPay.FlexPay.entities.Wallet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionMapper {


    public Transaction toEntity(TransferRequest request ,Wallet sender, Wallet reciever){
        Transaction transaction= new Transaction();
        transaction.setSenderWallet(sender);
        transaction.setReceiverWallet(reciever);
        transaction.setAmount(request.getAmount());

        return transaction;

    }

    public Transaction toWithdrawTransaction(Wallet sender,BigDecimal amount){
        Transaction transaction=new Transaction();
        transaction.setSenderWallet(sender);
        transaction.setReceiverWallet(null);
        transaction.setAmount(amount);
        transaction.setStatus(Status.SUCCESS);
        transaction.setType(TransactionType.WITHDRAW);
        return transaction;
    }

   public Transaction toDepositTransaction(Wallet receiver, BigDecimal amount){
       Transaction transaction= new Transaction();
       transaction.setReceiverWallet(receiver);
       transaction.setSenderWallet(null);
       transaction.setAmount(amount);
       transaction.setStatus(Status.SUCCESS);
       transaction.setType(TransactionType.DEPOSIT);
       return transaction;

   }
   public TransactionDetailResponse toTransactionDetail(Transaction tx){
       TransactionDetailResponse response = new TransactionDetailResponse();
       response.setTransactionId(tx.getId());
       response.setAmount(tx.getAmount());
       response.setType(tx.getType());
       response.setStatus(tx.getStatus());
       response.setCreatedAt(tx.getCreatedAt());
       response.setSenderWalletId(
               tx.getSenderWallet() != null
                       ? tx.getSenderWallet().getId()
                       : null
       );

       response.setReceiverWalletId(
               tx.getReceiverWallet() != null
                       ? tx.getReceiverWallet().getId()
                       : null
       );

       return response;
   }

   public TransactionHistoryResponse transactionHistoryResponse(Transaction tx){
        TransactionHistoryResponse response = new TransactionHistoryResponse();
        response.setTransactionId(tx.getId());
        response.setAmount(tx.getAmount());
        response.setType(tx.getType());
        response.setStatus(tx.getStatus());
        response.setCreatedAt(tx.getCreatedAt());
       response.setSenderWalletId(
               tx.getSenderWallet() != null
                       ? tx.getSenderWallet().getId()
                       : null
       );

       response.setReceiverWalletId(
               tx.getReceiverWallet() != null
                       ? tx.getReceiverWallet().getId()
                       : null
       );

        return response;

   }

    public TransferResponse toTransferResponse(String message , Long id){
        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setMessage(message);
        transferResponse.setTransactionId(id);
        return transferResponse;
    }

   public DepositResponse toDepositResponse(String message, BigDecimal Balance){

        DepositResponse depositResponse = new DepositResponse();
        depositResponse.setBalance(Balance);
        depositResponse.setMessage(message);
        return depositResponse;

   }

   public WithdrawResponse toWithdrawResponse(String message,BigDecimal balance){
        WithdrawResponse response=new WithdrawResponse();
        response.setBalance(balance);
        response.setMessage(message);

        return response;
   }
}
