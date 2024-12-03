package com.sparta.msa_exam.product.domain.dto;

import com.sparta.msa_exam.product.model.entity.Product;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductResponseDto implements Serializable {
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
