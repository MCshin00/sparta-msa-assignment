package com.sparta.msa_exam.product.domain.dto;

import com.sparta.msa_exam.product.model.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductRequestDto {
    private String name;
    private Integer supply_price;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .supply_price(supply_price)
                .build();
    }
}
