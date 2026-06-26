package org.example.t1academyhome.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.t1academyhome.entity.ProductType;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDto {
    @NotBlank(message = "Номер счета не должен быть пустым")
    @Size(max = 50, message = "Номер счета не должен превышать 50 символов")
    private String accountNumber;

    @NotNull(message = "Баланс должен быть указан")
    @PositiveOrZero(message = "Баланс не может быть отрицательным")
    private BigDecimal balance;

    @NotNull(message = "Тип продукта должен быть указан (ACCOUNT или CARD)")
    private ProductType productType;

    @NotNull(message = "ID пользователя должен быть указан")
    private Long userId;
}
