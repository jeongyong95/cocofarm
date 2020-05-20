package com.jbnu.cocofarm.service.product;

import java.util.List;

import com.jbnu.cocofarm.domain.product.dto.ProductDetailDto.DetailRegisterDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductDisplayDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductRegisterDto;
import com.jbnu.cocofarm.domain.seller.Seller;

public interface ProductService {

    void registerProduct(ProductRegisterDto registerDto, DetailRegisterDto detailDto);

    void updateStock(int orderedQuantity, Long productId);

    ProductDisplayDto findById(Long id);

    List<ProductDisplayDto> findAll();

    List<ProductDisplayDto> findBySeller(Seller seller);
}