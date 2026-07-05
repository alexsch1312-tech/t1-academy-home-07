package org.example.payment.controller;

import lombok.RequiredArgsConstructor;
import org.example.payment.dto.PaymentRequestDto;
import org.example.payment.dto.ProductResponseDto;
import org.example.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/helthcheck")
    @ResponseStatus(HttpStatus.OK)
    public String getClientProducts() {
        return "Hello";
    }

    @GetMapping("/user-products/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDto> getClientProducts(@PathVariable Long userId) {
        return paymentService.getUserProducts(userId);
    }

    @PostMapping("/execute")
    @ResponseStatus(HttpStatus.OK)
    public String execute(@RequestBody PaymentRequestDto requestDto) {
        return paymentService.executePayment(requestDto);
    }
}
