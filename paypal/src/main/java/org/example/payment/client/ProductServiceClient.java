package org.example.payment.client;

import lombok.RequiredArgsConstructor;
import org.example.payment.dto.ProductResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceClient {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "/api/v1/products";

    public List<ProductResponseDto> getProductsByUserId(Long userId) {
        String url = BASE_URL + "?userId=" + userId;
        ResponseEntity<List<ProductResponseDto>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductResponseDto>>() {
                }
        );
        return response.getBody();
    }

    public ProductResponseDto getProductById(Long productId) {
        String url = BASE_URL + "/" + productId;
        return restTemplate.getForObject(url, ProductResponseDto.class);
    }

    public void updateProduct(Long productId, ProductResponseDto updatedProduct) {
        String url = BASE_URL + "/" + productId;
        restTemplate.put(url, updatedProduct);
    }
}
