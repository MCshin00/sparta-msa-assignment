package com.sparta.msa_exam.order.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderRequestDto {
    private Long product_id;
    private Integer amount;
}
