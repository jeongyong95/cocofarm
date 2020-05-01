package com.jbnu.cocofarm.domain.orders;

import java.util.List;

import com.jbnu.cocofarm.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersTempRepository extends JpaRepository<OrdersTemp, String> {
    // 기본 CRUD 이외에 필요한 query를 추가합니다. querydsl 참고

    List<OrdersTemp> findByUser(User user);
}