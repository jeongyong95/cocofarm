package com.jbnu.cocofarm.service.product;

import java.util.List;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.product.ProductDto.ProductRequestDto;
import com.jbnu.cocofarm.domain.seller.Seller;

/**
 * ProductService
 */
public interface ProductService {
    void registerProduct(ProductRequestDto productRequestDto);

    void updateProduct(ProductRequestDto productRequestDto);

    void deleteProduct(Long productId);

    Product searchProductById(Long productId);

    List<Product> getAllProducts();

    List<Product> searchProductByName(String searchKeyword);

    List<Product> searchProductBySeller(Seller seller);

    // productDetail 부분

    ProductDetail searchProductDetailById(Long productDetailId);
}