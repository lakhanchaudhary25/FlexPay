package com.FlexPay.FlexPay.controller;


import com.FlexPay.FlexPay.dto.request.DepositRequest;
import com.FlexPay.FlexPay.dto.request.WithdrawRequest;
import com.FlexPay.FlexPay.dto.response.BalanceEnquiryResponse;
import com.FlexPay.FlexPay.dto.response.DepositResponse;
import com.FlexPay.FlexPay.dto.response.TransactionHistoryResponse;
import com.FlexPay.FlexPay.dto.response.WithdrawResponse;
import com.FlexPay.FlexPay.services.TransferService;
import com.FlexPay.FlexPay.services.WalletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private final WalletService walletService;
    private final TransferService transferService;
    public WalletController(WalletService walletService, TransferService transferService) {
        this.walletService = walletService;
        this.transferService = transferService;
    }

    @GetMapping("/{walletId}/balance")
    public BalanceEnquiryResponse checkBalance(@Valid @PathVariable Long walletId){
        return walletService.checkBalance(walletId);
    }

    @PostMapping("/{walletId}/deposits")
    public DepositResponse depositMoney( @PathVariable Long walletId ,
                                         @Valid @RequestBody DepositRequest request) {
        return walletService.depositMoney(walletId, request);
    }

    @GetMapping("/{walletId}/Transactions")
    public List<TransactionHistoryResponse> getTransactionHistory(@PathVariable Long walletId ){

        return transferService.getTransactionHistoryResponse(walletId);
    }
    @PostMapping("/{walletId}/withdrawals")
    public WithdrawResponse withdraw(@PathVariable Long walletId, @Valid @RequestBody WithdrawRequest withdrawRequest){

        return walletService.withdrawMoney(walletId,withdrawRequest);
    }

}
