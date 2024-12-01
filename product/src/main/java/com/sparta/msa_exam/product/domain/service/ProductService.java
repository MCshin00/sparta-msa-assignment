package com.sparta.msa_exam.product.domain.service;

import com.sparta.msa_exam.product.domain.dto.ProductIdResponseDto;
import com.sparta.msa_exam.product.domain.dto.ProductRequestDto;
import com.sparta.msa_exam.product.model.entity.Product;
import com.sparta.msa_exam.product.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductIdResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = requestDto.toEntity();
        productRepository.save(product);

        return ProductIdResponseDto.builder()
                .product_id(product.getProduct_id())
                .build();
    }
}
