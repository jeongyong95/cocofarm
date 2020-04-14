package com.jbnu.cocofarm.service;

import java.util.List;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.user.Seller;

/**
 * ProductService
 */
public interface ProductService {
    void registerProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Long id);

    List<Product> getAllProducts();

    List<Product> searchProductByName(String searchKeyword);

    List<Product> searchProductBySeller(Seller seller);
}