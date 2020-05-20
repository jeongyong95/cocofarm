package com.jbnu.cocofarm.domain.seller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findBySellerCode(String sellerCode);
}