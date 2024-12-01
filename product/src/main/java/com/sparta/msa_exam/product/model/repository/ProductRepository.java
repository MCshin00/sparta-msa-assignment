package com.sparta.msa_exam.product.model.repository;

import com.sparta.msa_exam.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
