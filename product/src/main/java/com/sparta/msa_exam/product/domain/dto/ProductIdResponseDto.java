package com.sparta.msa_exam.product.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductIdResponseDto {
    private Long product_id;
}
