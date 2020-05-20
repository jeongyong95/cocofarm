package com.jbnu.cocofarm.domain.order.repository;

import com.jbnu.cocofarm.domain.order.OrderTotal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTotalRepository extends JpaRepository<OrderTotal, Long> {

}