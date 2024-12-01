package com.sparta.msa_exam.order.domain.client;

import com.sparta.msa_exam.order.domain.dto.ProductResponseDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/products")
    ResponseEntity<List<ProductResponseDto>> getProducts();
}
