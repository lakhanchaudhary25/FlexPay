package com.FlexPay.FlexPay.services;

import com.FlexPay.FlexPay.dto.request.TransferRequest;
import com.FlexPay.FlexPay.dto.response.TransactionDetailResponse;
import com.FlexPay.FlexPay.dto.response.TransactionHistoryResponse;
import com.FlexPay.FlexPay.dto.response.TransferResponse;

import java.util.List;

public interface TransferService {
    TransferResponse transfer(TransferRequest request);
        TransactionDetailResponse getTransactionDetail(Long id);
 List<TransactionHistoryResponse> getTransactionHistoryResponse(Long id);
}
