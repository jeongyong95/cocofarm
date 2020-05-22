package com.jbnu.cocofarm.domain.order.repository;

import java.util.List;

import com.jbnu.cocofarm.domain.order.OrderProduct;
import com.jbnu.cocofarm.domain.order.OrderTotal;
import com.jbnu.cocofarm.domain.product.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    List<OrderProduct> findByOrderTotal(OrderTotal orderTotal);

    List<OrderProduct> findByProduct(Product product);

}