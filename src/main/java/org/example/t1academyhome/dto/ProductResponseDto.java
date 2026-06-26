package org.example.t1academyhome.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.t1academyhome.entity.Product;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private String productType;
    private Long userId;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.accountNumber = product.getAccountNumber();
        this.balance = product.getBalance();
        this.productType = product.getProductType().name();
        this.userId = product.getUser().getId();
    }
}
