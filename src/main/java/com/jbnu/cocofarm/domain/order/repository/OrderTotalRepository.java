package com.jbnu.cocofarm.domain.order.repository;

import java.util.List;

import com.jbnu.cocofarm.domain.customer.Customer;
import com.jbnu.cocofarm.domain.order.OrderTotal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTotalRepository extends JpaRepository<OrderTotal, Long> {

    List<OrderTotal> findByCustomer(Customer customer);
}