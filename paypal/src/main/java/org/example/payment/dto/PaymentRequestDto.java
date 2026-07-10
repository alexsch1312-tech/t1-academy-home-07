package org.example.payment.dto;

import java.math.BigDecimal;

public record PaymentRequestDto(Long productId,
                                BigDecimal amount,
                                String description) {

}
