package com.jbnu.cocofarm.service;

import java.util.List;
import java.util.Optional;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public void registerProduct(Product product) {
        repo.save(product);
        System.out.println("상품등록 성공");
    }

    @Override
    public void updateProduct(Product product) {
        repo.save(product);
        System.out.println("상품수정 성공");
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> findedProduct = repo.findById(id);
        findedProduct.ifPresent(target -> {
            repo.delete(target);
            System.out.println("상품삭제 성공");
        });
    }

    @Override
    public List<Product> searchProductByName(String searchKeyword) {
        List<Product> searchResultList = repo.findByNameContaining(searchKeyword);
        return searchResultList;
    }

}