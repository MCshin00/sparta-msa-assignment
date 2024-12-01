package com.sparta.msa_exam.order.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderIdResponseDto {
    private Long order_id;
}
