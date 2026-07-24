package com.FlexPay.FlexPay.dto.request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    @NotNull
    private Long senderWallet;
    @NotNull
    private Long receiverWallet;
    @NotNull
    @Positive(message = "negative amount not allowed")
    private BigDecimal amount;
}
