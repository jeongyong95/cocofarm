package com.jbnu.cocofarm.domain.basket;

import java.util.List;

import com.jbnu.cocofarm.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BasketRepository
 */
public interface BasketRepository extends JpaRepository<Basket, String> {
    // 기본 CRUD 이외에 필요한 query를 추가합니다. querydsl 참고

    // 장바구니 조회
    List<Basket> findByUser(User user);
}