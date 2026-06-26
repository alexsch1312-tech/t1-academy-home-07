package org.example.t1academyhome.service;

import lombok.RequiredArgsConstructor;
import org.example.t1academyhome.dto.ProductRequestDto;
import org.example.t1academyhome.dto.ProductResponseDto;
import org.example.t1academyhome.entity.Product;
import org.example.t1academyhome.entity.User;
import org.example.t1academyhome.exception.ResourceNotFoundException;
import org.example.t1academyhome.repository.ProductRepository;
import org.example.t1academyhome.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // Create
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с id " + dto.getUserId() + " не найден"));

        Product product = new Product(dto.getAccountNumber(), dto.getBalance(), dto.getProductType(), user);
        Product savedProduct = productRepository.save(product);
        return new ProductResponseDto(savedProduct);
    }

    // Read (All or By UserId)
    public List<ProductResponseDto> getProducts(Long userId) {
        if (userId != null) {
            return productRepository.findByUserId(userId).stream()
                    .map(ProductResponseDto::new)
                    .collect(Collectors.toList());
        }
        return productRepository.findAll().stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    // Read (One)
    public ProductResponseDto getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(ProductResponseDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id " + productId + " не найден"));
    }

    // Update
    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto dto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id " + productId + " не найден"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с id " + dto.getUserId() + " не найден"));

        product.setAccountNumber(dto.getAccountNumber());
        product.setBalance(dto.getBalance());
        product.setProductType(dto.getProductType());
        product.setUser(user);

        return new ProductResponseDto(product);
    }

    // Delete
    @Transactional
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Продукт с id " + productId + " не найден");
        }
        productRepository.deleteById(productId);
    }
}
