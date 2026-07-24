package com.FlexPay.FlexPay.services;

import com.FlexPay.FlexPay.dto.request.DepositRequest;
import com.FlexPay.FlexPay.dto.request.WithdrawRequest;
import com.FlexPay.FlexPay.dto.response.BalanceEnquiryResponse;
import com.FlexPay.FlexPay.dto.response.DepositResponse;
import com.FlexPay.FlexPay.dto.response.WithdrawResponse;
import com.FlexPay.FlexPay.entities.Transaction;
import com.FlexPay.FlexPay.entities.Wallet;
import com.FlexPay.FlexPay.exception.InsufficientBalanceException;
import com.FlexPay.FlexPay.exception.WalletNotFoundException;
import com.FlexPay.FlexPay.mapper.TransactionMapper;
import com.FlexPay.FlexPay.mapper.WalletMapper;
import com.FlexPay.FlexPay.repository.TransactionRepository;
import com.FlexPay.FlexPay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {


    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    public WalletServiceImpl(WalletRepository walletRepository,
                             WalletMapper walletMapper, TransactionMapper transactionMapper, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
        this.transactionMapper = transactionMapper;

        this.transactionRepository = transactionRepository;
    }


    @Override
    public BalanceEnquiryResponse checkBalance(Long id) {
        Wallet wallet =walletRepository.findById(id)
                .orElseThrow(()-> new WalletNotFoundException("wallet not found."));
        return walletMapper.toResponse(wallet) ;
    }
    @Transactional
    @Override
    public DepositResponse depositMoney(Long id, DepositRequest request) {
        Wallet wallet= walletRepository.findById(id)
                .orElseThrow(()->new WalletNotFoundException("Wallet doesn't exists"));
        wallet.setBalance(wallet.getBalance().add(request.getAmount()));

        Transaction tx= transactionMapper.toDepositTransaction(wallet,request.getAmount());
        transactionRepository.save(tx);

        return transactionMapper.toDepositResponse("deposit successful",wallet.getBalance());
    }
    @Transactional
    @Override
    public WithdrawResponse withdrawMoney(Long id, WithdrawRequest request) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(()->new WalletNotFoundException("Wallet doesn't exists"));
        if(wallet.getBalance().compareTo(request.getAmount())<0)
            throw new InsufficientBalanceException("not enough funds.");
        wallet.setBalance(wallet.getBalance().subtract(request.getAmount()));
        Transaction transaction= transactionMapper.toWithdrawTransaction(wallet,request.getAmount());
        transactionRepository.save(transaction);
        return transactionMapper.toWithdrawResponse("Money deducted.",wallet.getBalance());
    }
}
