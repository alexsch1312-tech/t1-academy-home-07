package org.example.payment.client;

import lombok.RequiredArgsConstructor;
import org.example.payment.dto.ProductResponseDto;
//import org.example.t1academyhome.dto.ProductResponseDto;
import org.example.payment.exception.IntegrationException;
import org.example.payment.exception.ProductNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceClient {

    private final RestTemplate restTemplate;

    private final String BASE_URL = "/api/v1/products";

    public List<ProductResponseDto> getProductsByUserId(Long userId) {
        String url = BASE_URL + "?userId=" + userId;
        try {
            ResponseEntity<List<ProductResponseDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProductResponseDto>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            throw new IntegrationException("Не удалось получить продукты пользователя: " + e.getMessage());
        }
    }

    public ProductResponseDto getProductById(Long productId) {
        String url = BASE_URL + "/" + productId;
        try {
            return restTemplate.getForObject(url, ProductResponseDto.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ProductNotFoundException("Продукт с ID " + productId + " не найден в сервисе продуктов.");
            }
            throw new IntegrationException("Ошибка при обращении к сервису продуктов: " + e.getMessage());
        } catch (Exception e) {
            throw new IntegrationException("Непредвиденная ошибка интеграции: " + e.getMessage());
        }
    }

    public void updateProduct(Long productId, ProductResponseDto updatedProduct) {
        String url = BASE_URL + "/" + productId;
        try {
            restTemplate.put(url, updatedProduct);
        } catch (Exception e) {
            throw new IntegrationException("Не удалось обновить баланс продукта: " + e.getMessage());
        }
    }
}
