package com.sparta.msa_exam.order.domain.controller;

import com.sparta.msa_exam.order.domain.dto.OrderRequestDto;
import com.sparta.msa_exam.order.domain.dto.OrderResponseDto;
import com.sparta.msa_exam.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestParam(required = false) boolean fail, @RequestBody OrderRequestDto requestDto) {
        OrderResponseDto responseDto = orderService.createOrder(fail, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long orderId) {
        OrderResponseDto responseDto = orderService.getOrder(orderId);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequestDto requestDto) {
        OrderResponseDto responseDto = orderService.updateOrder(orderId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
