package com.jbnu.cocofarm.domain.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    // 구매자 주문 조회
    List<Orders> findByUser(User user);
}