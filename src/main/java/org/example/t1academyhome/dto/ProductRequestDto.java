package org.example.t1academyhome.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.t1academyhome.entity.ProductType;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDto {
    private String accountNumber;
    private BigDecimal balance;
    private ProductType productType;
    private Long userId;
}
