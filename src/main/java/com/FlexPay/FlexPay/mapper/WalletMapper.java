package com.FlexPay.FlexPay.mapper;

import com.FlexPay.FlexPay.dto.response.BalanceEnquiryResponse;
import com.FlexPay.FlexPay.entities.Wallet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WalletMapper {

    public BalanceEnquiryResponse toResponse(Wallet sender){
         BalanceEnquiryResponse response = new BalanceEnquiryResponse();
         response.setWalletId(sender.getId());
         response.setBalance(sender.getBalance());
        return response;
    }


}
