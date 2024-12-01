package com.sparta.msa_exam.product.domain.controller;

import com.sparta.msa_exam.product.domain.dto.ProductIdResponseDto;
import com.sparta.msa_exam.product.domain.dto.ProductRequestDto;
import com.sparta.msa_exam.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductIdResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {
        ProductIdResponseDto responseDto = productService.createProduct(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
