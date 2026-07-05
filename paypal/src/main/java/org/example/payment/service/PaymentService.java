package org.example.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payment.client.ProductServiceClient;
import org.example.payment.dto.PaymentRequestDto;
import org.example.payment.dto.ProductResponseDto;
import org.example.payment.exception.InsufficientFundsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final ProductServiceClient productServiceClient;

    // Получение продуктов пользователя
    public List<ProductResponseDto> getUserProducts(Long userId) {
        log.info("Запрос продуктов для пользователя с ID: {}", userId);
        return productServiceClient.getProductsByUserId(userId);
    }

    // Исполнения платежа
    public String executePayment(PaymentRequestDto paymentRequest) {
        log.info("Старт платежа для продукта ID: {} на сумму: {}", paymentRequest.productId(), paymentRequest.amount());

        // 1. Проверка существования продукта
        ProductResponseDto product = productServiceClient.getProductById(paymentRequest.productId());

        // 2. Проверка достаточности средств
        if (product.getBalance().compareTo(paymentRequest.amount()) < 0) {
            throw new InsufficientFundsException(
                    String.format("Недостаточно средств. На балансе: %s, требуется: %s",
                            product.getBalance(), paymentRequest.amount())
            );
        }

        // 3. Списание средств со счета
        product.setBalance(product.getBalance().subtract(paymentRequest.amount()));

        // 4. Отправка обратно в сервис продуктов
        productServiceClient.updateProduct(product.getId(), product);

        log.info("Платеж успешно выполнен для продукта ID: {}", product.getId());
        return "Платеж успешно выполнен. Списано: " + paymentRequest.amount();
    }
}
