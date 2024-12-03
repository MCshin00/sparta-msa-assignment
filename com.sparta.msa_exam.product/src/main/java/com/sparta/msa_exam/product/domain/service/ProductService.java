package com.sparta.msa_exam.product.domain.service;

import com.sparta.msa_exam.product.domain.dto.ProductIdResponseDto;
import com.sparta.msa_exam.product.domain.dto.ProductRequestDto;
import com.sparta.msa_exam.product.domain.dto.ProductResponseDto;
import com.sparta.msa_exam.product.model.entity.Product;
import com.sparta.msa_exam.product.model.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    @CacheEvict(cacheNames = "itemAllCache", allEntries = true)
    public ProductIdResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = requestDto.toEntity();
        productRepository.save(product);

        return ProductIdResponseDto.builder()
                .product_id(product.getProduct_id())
                .build();
    }

    @Cacheable(cacheNames = "productAllCache", key = "methodName")
    public List<ProductResponseDto> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductResponseDto::from)
                .collect(Collectors.toList());
    }
}
