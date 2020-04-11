package com.jbnu.cocofarm.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findById(Long id);

    Optional<Seller> findBySellerCode(String sellerCode);
}