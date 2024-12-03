package com.sparta.msa_exam.order.domain.dto;

import com.sparta.msa_exam.order.model.entity.Order;
import com.sparta.msa_exam.order.model.entity.OrderProduct;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderResponseDto implements Serializable {
    private Long order_id;
    private List<Long> product_ids;

    public static OrderResponseDto from(Order order) {
        List<Long> productIds = order.getOrderProducts().stream()
                .map(OrderProduct::getProduct_id)
                .collect(Collectors.toList());

        return OrderResponseDto.builder()
                .order_id(order.getOrder_id())
                .product_ids(productIds)
                .build();
    }
}
