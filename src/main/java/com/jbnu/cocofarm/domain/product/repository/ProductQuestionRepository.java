package com.jbnu.cocofarm.domain.product.repository;

import java.util.List;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductQuestion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductQuestionRepository extends JpaRepository<ProductQuestion, Long> {

    List<ProductQuestion> findByProduct(Product product);

    ProductQuestion findByGroupNumber(Long groupNumber);


}