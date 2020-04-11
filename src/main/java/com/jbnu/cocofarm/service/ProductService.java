package com.jbnu.cocofarm.service;

import java.util.List;

import com.jbnu.cocofarm.domain.product.Product;

/**
 * ProductService
 */
public interface ProductService {
    void registerProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Long id);

    List<Product> searchProductByName(String searchKeyword);
}