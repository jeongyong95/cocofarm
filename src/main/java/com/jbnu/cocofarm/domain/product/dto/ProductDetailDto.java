package com.jbnu.cocofarm.domain.product.dto;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductDetail;

import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductDetailDto {

    @Data
    @NoArgsConstructor
    public static class DetailRegisterDto {

        private int stock;

        public ProductDetail toEntity(Product product) {
            return ProductDetail.builder().stock(stock).product(product).build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class DetailUpdateDto {
        private int stock;

        public DetailUpdateDto(ProductDetail detail) {
            this.stock = detail.getStock();
        }
    }
}