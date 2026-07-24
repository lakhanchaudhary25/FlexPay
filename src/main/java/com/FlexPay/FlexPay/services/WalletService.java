package com.FlexPay.FlexPay.services;


import com.FlexPay.FlexPay.dto.request.DepositRequest;
import com.FlexPay.FlexPay.dto.request.WithdrawRequest;
import com.FlexPay.FlexPay.dto.response.BalanceEnquiryResponse;
import com.FlexPay.FlexPay.dto.response.DepositResponse;
import com.FlexPay.FlexPay.dto.response.WithdrawResponse;


public interface WalletService {

BalanceEnquiryResponse checkBalance(Long id);

 DepositResponse depositMoney(Long id, DepositRequest request);
 WithdrawResponse withdrawMoney(Long id , WithdrawRequest request);


}
