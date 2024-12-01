package com.sparta.msa_exam.order.domain.controller;

import com.sparta.msa_exam.order.domain.dto.OrderIdResponseDto;
import com.sparta.msa_exam.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderIdResponseDto> createOrder() {
        OrderIdResponseDto responseDto = orderService.createOrder();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
