package com.jbnu.cocofarm.domain.product;

import java.util.List;

import com.jbnu.cocofarm.domain.user.Seller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 기본 CRUD 이외에 필요한 query를 추가합니다. querydsl 참고
    // queryDSL 적용을 못하면 그냥 JPA에서 제공해주는 쿼리 메소드를 사용합니다.

    // 상품 이름 검색 조회
    List<Product> findByNameContaining(String searchKeyword);

    // 상품 카테고리 and 이름 검색 조회
    List<Product> findByCategoryAndNameContaining(Category category, String searchKeyword);

    // 판매 상품 조회(판매자가)
    List<Product> findBySeller(Seller seller);
}