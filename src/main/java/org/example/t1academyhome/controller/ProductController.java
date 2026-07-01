package org.example.t1academyhome.controller;

import lombok.RequiredArgsConstructor;
import org.example.t1academyhome.dto.ProductRequestDto;
import org.example.t1academyhome.dto.ProductResponseDto;
import org.example.t1academyhome.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Устанавливает код 201 Created
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto dto) {
        return productService.createProduct(dto);
    }

    @GetMapping
    public List<ProductResponseDto> getProducts(@RequestParam(required = false) Long userId) {
        return productService.getProducts(userId);
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/{productId}")
    public ProductResponseDto updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequestDto dto) {
        return productService.updateProduct(productId, dto);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Устанавливает код 204 No Content
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
