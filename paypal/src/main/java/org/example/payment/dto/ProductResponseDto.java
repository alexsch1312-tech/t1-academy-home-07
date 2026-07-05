package org.example.payment.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private String productType;
    private Long userId;
}
