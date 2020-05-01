package com.jbnu.cocofarm.domain.orders;

import java.util.List;

import com.jbnu.cocofarm.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    // 구매자 주문 조회
    List<Orders> findByUser(User user);
}