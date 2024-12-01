package com.sparta.msa_exam.product.domain.dto;

import com.sparta.msa_exam.product.model.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDto {
    private Long product_id;
    private String name;
    private Integer supply_price;

    public static ProductResponseDto from(Product product) {
        return ProductResponseDto.builder()
                .product_id(product.getProduct_id())
                .name(product.getName())
                .supply_price(product.getSupply_price())
                .build();
    }
}
