package com.jbnu.cocofarm.domain.review;

import java.util.List;

import com.jbnu.cocofarm.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ReviewRepository
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 기본 CRUD 이외에 필요한 query를 추가합니다. querydsl 참고

    // 벌점으로 상품리뷰 조회
    List<Review> findByStarPointGreaterThanEqual(Integer starPoint);

    // 상품별 상품리뷰 조회
    List<Review> findByProduct(Long id);

    // 구매자별 상품리뷰 조회
    List<Review> findByUser(User user);
}