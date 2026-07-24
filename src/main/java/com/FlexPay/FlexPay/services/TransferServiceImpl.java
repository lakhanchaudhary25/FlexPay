package com.FlexPay.FlexPay.services;
import com.FlexPay.FlexPay.dto.request.TransferRequest;
import com.FlexPay.FlexPay.dto.response.TransactionDetailResponse;
import com.FlexPay.FlexPay.dto.response.TransactionHistoryResponse;
import com.FlexPay.FlexPay.dto.response.TransferResponse;
import com.FlexPay.FlexPay.entities.Transaction;
import com.FlexPay.FlexPay.entities.Wallet;
import com.FlexPay.FlexPay.exception.*;
import com.FlexPay.FlexPay.mapper.TransactionMapper;
import com.FlexPay.FlexPay.repository.TransactionRepository;
import com.FlexPay.FlexPay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.FlexPay.FlexPay.enums.Status.SUCCESS;
import static com.FlexPay.FlexPay.enums.TransactionType.TRANSFER;


@Service
public class TransferServiceImpl implements TransferService{


    private final TransactionMapper mapper;
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransferServiceImpl(TransactionMapper mapper,TransactionRepository transactionRepository1, WalletRepository walletRepository1){
        this.mapper=mapper;
        this.transactionRepository = transactionRepository1;
        this.walletRepository = walletRepository1;
    }
    @Transactional
    @Override
    public TransferResponse transfer(TransferRequest request) {

        //cheap check
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalAmountException("Invalid amount entered.");
        }
        //cheap check
        if(request.getSenderWallet()
                .equals(request.getReceiverWallet())){

            throw new SelfTransferException("Self Transfer is not allowed");
        }
        //database query
        Wallet sender = walletRepository.findById(request.getSenderWallet())
                .orElseThrow(()->new WalletNotFoundException("Wallet Not Found"));
        Wallet receiver = walletRepository.findById(request.getReceiverWallet())
                .orElseThrow(()->new WalletNotFoundException("Wallet Not Found"));

        if(sender.getBalance()
                .compareTo(request.getAmount()) < 0){
            throw new InsufficientBalanceException("Not Enough Funds");
        }
        sender.setBalance(sender.getBalance().subtract(request.getAmount()));
        receiver.setBalance(receiver.getBalance().add(request.getAmount()));

        Transaction transaction = mapper.toEntity(request,sender,receiver);
        transaction.setStatus(SUCCESS);
        transaction.setType(TRANSFER);
        transactionRepository.save(transaction);

        return mapper.toTransferResponse("Transfer successfull",transaction.getId());

    }

    @Override
    public TransactionDetailResponse getTransactionDetail(Long id) {
      Transaction tx=  transactionRepository.findById(id)
                .orElseThrow(()->new TransactionNotFoundException("No transaction available"));

        return mapper.toTransactionDetail(tx);
    }

    @Override
    public List<TransactionHistoryResponse> getTransactionHistoryResponse(Long id) {
        walletRepository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found."));
        List<Transaction>transaction= transactionRepository.findBySenderWallet_IdOrReceiverWallet_IdOrderByCreatedAtDesc(id,id);
        return transaction.stream().map(mapper::transactionHistoryResponse).toList();
    }
}
