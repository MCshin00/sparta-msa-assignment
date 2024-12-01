package com.sparta.msa_exam.order.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderUpdateRequestDto {
    private Long product_id;
    private Integer amount;
}
