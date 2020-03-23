package com.jbnu.cocofarm.repository;

import com.jbnu.cocofarm.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderRepository
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}