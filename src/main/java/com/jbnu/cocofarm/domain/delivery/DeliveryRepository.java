package com.jbnu.cocofarm.domain.delivery;

import java.util.List;

import com.jbnu.cocofarm.domain.order.OrderProduct;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByOrderProduct(OrderProduct orderProduct);
}