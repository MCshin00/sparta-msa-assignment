package com.sparta.msa_exam.order.model.repository;

import com.sparta.msa_exam.order.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
