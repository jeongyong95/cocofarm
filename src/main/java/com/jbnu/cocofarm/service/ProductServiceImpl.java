package com.jbnu.cocofarm.service;

import java.util.List;
import java.util.Optional;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.product.ProductDetailRepository;
import com.jbnu.cocofarm.domain.product.ProductRepository;
import com.jbnu.cocofarm.domain.user.Seller;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * ProductServiceImpl
 */
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;
    private ProductDetailRepository productDetailRepo;

    @Override
    public void registerProduct(Product product, Integer stockNumber) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setStockNumber(stockNumber);
        productRepo.save(product);
        productDetail.setProduct(product);
        productDetailRepo.save(productDetail);

        System.out.println("상품등록 성공");
    }

    @Override
    public void updateProduct(Product product) {
        productRepo.save(product);
        System.out.println("상품수정 성공");
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> findedProduct = productRepo.findById(id);
        findedProduct.ifPresent(target -> {
            productRepo.delete(target);
            System.out.println("상품삭제 성공");
        });
    }

    @Override
    public Product searchProductById(Long productId) {
        Optional<Product> resultProduct = productRepo.findById(productId);
        if (resultProduct.isPresent()) {
            return resultProduct.get();
        }
        return null;

    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> resultList = productRepo.findAll();
        return resultList;
    }

    @Override
    public List<Product> searchProductByName(String searchKeyword) {
        List<Product> searchResultList = productRepo.findByNameContaining(searchKeyword);
        return searchResultList;
    }

    @Override
    public List<Product> searchProductBySeller(Seller seller) {
        return productRepo.findBySeller(seller);
    }

    @Override
    public ProductDetail searchProductDetailById(Long productDetailId) {
        Optional<ProductDetail> resultProductDetail = productDetailRepo.findById(productDetailId);
        if (resultProductDetail.isPresent()) {
            return resultProductDetail.get();
        }
        return null;
    }

}