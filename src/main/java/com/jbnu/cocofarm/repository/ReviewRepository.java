package com.jbnu.cocofarm.repository;

import java.util.List;

import com.jbnu.cocofarm.domain.Review;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ReviewRepository
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 기본 CRUD 이외에 필요한 query를 추가합니다. querydsl 참고

    List<Review> findByStarPointGreaterThanEqual(Integer starPoint);
}