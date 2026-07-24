package com.FlexPay.FlexPay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {

    private String message;

    private Long transactionId;
}
