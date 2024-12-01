package com.sparta.msa_exam.order.domain.service;

import com.sparta.msa_exam.order.domain.client.ProductClient;
import com.sparta.msa_exam.order.domain.dto.OrderIdResponseDto;
import com.sparta.msa_exam.order.model.entity.Order;
import com.sparta.msa_exam.order.model.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Transactional
    public OrderIdResponseDto createOrder() {
        Order order = Order.builder().build();
        orderRepository.save(order);

        return OrderIdResponseDto.builder()
                .order_id(order.getOrder_id())
                .build();
    }
}
