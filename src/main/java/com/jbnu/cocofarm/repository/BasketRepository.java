package com.jbnu.cocofarm.repository;

import com.jbnu.cocofarm.domain.Basket;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BasketRepository
 */
public interface BasketRepository extends JpaRepository<Basket, String> {

}