package com.jbnu.cocofarm.repository;

import com.jbnu.cocofarm.domain.Category;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoryRepository
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}