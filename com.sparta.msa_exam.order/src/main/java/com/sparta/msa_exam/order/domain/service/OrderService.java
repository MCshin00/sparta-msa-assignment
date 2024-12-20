package com.sparta.msa_exam.order.domain.service;

import com.sparta.msa_exam.order.domain.client.ProductClient;
import com.sparta.msa_exam.order.domain.dto.OrderRequestDto;
import com.sparta.msa_exam.order.domain.dto.OrderResponseDto;
import com.sparta.msa_exam.order.domain.dto.ProductResponseDto;
import com.sparta.msa_exam.order.model.entity.Order;
import com.sparta.msa_exam.order.model.entity.OrderProduct;
import com.sparta.msa_exam.order.model.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Transactional
    @CircuitBreaker(name = "orderService", fallbackMethod = "fallbackOrder")
    public OrderResponseDto createOrder(boolean fail, OrderRequestDto requestDto) {
        if (fail) {
            throw new RuntimeException("product 요청 실패");
        }

        Order order = Order.builder()
                .orderProducts(new ArrayList<>())
                .build();
        orderRepository.save(order);

        validateProductId(requestDto);

        OrderProduct orderProduct = OrderProduct.builder()
                .order(order)
                .product_id(requestDto.getProduct_id())
                .amount(requestDto.getAmount())
                .build();
        order.getOrderProducts().add(orderProduct);

        orderRepository.save(order);

        return OrderResponseDto.from(order);
    }

    @Cacheable(cacheNames = "orderCache", key = "args[0]")
    public OrderResponseDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

        return OrderResponseDto.from(order);
    }

    @Transactional
    @CircuitBreaker(name = "orderService", fallbackMethod = "fallbackOrder")
    public OrderResponseDto updateOrder(Long orderId, OrderRequestDto requestDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

        validateProductId(requestDto);

        Optional<OrderProduct> existingOrderProduct = order.getOrderProducts()
                .stream()
                .filter(orderProduct -> orderProduct.getProduct_id().equals(requestDto.getProduct_id()))
                .findFirst();

        if (existingOrderProduct.isPresent()) {
            OrderProduct orderProduct = existingOrderProduct.get();
            orderProduct.updateAmount(requestDto.getAmount());
        } else {
            OrderProduct orderProduct = OrderProduct.builder()
                    .order(order)
                    .product_id(requestDto.getProduct_id())
                    .amount(requestDto.getAmount())
                    .build();
            order.getOrderProducts().add(orderProduct);
        }

        orderRepository.save(order);

        return OrderResponseDto.from(order);
    }

    private void validateProductId(OrderRequestDto requestDto) {
        if (requestDto.getProduct_id() == null) {
            throw new IllegalArgumentException("추가할 상품을 지정해야 합니다.");
        }

        List<ProductResponseDto> productList = productClient.getProducts().getBody();

        if (productList == null || productList.isEmpty()) {
            throw new IllegalArgumentException("조회된 상품이 없습니다.");
        }

        boolean ProductsExist = productList.stream()
                .anyMatch(product -> product.getProduct_id().equals(requestDto.getProduct_id()));

        if (!ProductsExist) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
    }

    public OrderResponseDto fallbackOrder(Throwable t) {
        log.info("fallback 발생 원인 : {}", t.getMessage());
        throw new RuntimeException("잠시 후에 주문 추가를 요청 해주세요.");
    }
}
