package org.example.payment.exception;

// Ошибка, если денег на балансе не хватает
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) { super(message); }
}
