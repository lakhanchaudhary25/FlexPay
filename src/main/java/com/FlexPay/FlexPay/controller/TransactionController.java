package com.FlexPay.FlexPay.controller;


import com.FlexPay.FlexPay.dto.request.TransferRequest;
import com.FlexPay.FlexPay.dto.response.TransactionDetailResponse;
import com.FlexPay.FlexPay.dto.response.TransactionHistoryResponse;
import com.FlexPay.FlexPay.dto.response.TransferResponse;
import com.FlexPay.FlexPay.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransferService transferService;

    public TransactionController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<TransferResponse> transferMoney(@Valid @RequestBody TransferRequest request){
       TransferResponse response= transferService.transfer(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDetailResponse> transactionDetail(@PathVariable Long id){
         TransactionDetailResponse response= transferService.getTransactionDetail(id);

         return ResponseEntity.ok(response);
    }



}
