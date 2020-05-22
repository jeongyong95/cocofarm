package com.jbnu.cocofarm.domain.cart;

import java.util.List;

import com.jbnu.cocofarm.domain.customer.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByCustomer(Customer customer);
}