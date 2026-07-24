package com.FlexPay.FlexPay.dto.response;


import com.FlexPay.FlexPay.Enum.Status;
import com.FlexPay.FlexPay.Enum.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryResponse {
    private Long transactionId;

    private TransactionType type;

    private Status status;

    private BigDecimal amount;

    private LocalDateTime createdAt;

    private Long senderWalletId;

    private Long receiverWalletId;

}
