package com.jbnu.cocofarm.repository;

import com.jbnu.cocofarm.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}