package com.jbnu.cocofarm.domain.order.repository;

import com.jbnu.cocofarm.domain.order.OrderProduct;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}