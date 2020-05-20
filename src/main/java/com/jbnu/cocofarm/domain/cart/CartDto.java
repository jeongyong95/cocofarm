package com.jbnu.cocofarm.domain.cart;

import com.jbnu.cocofarm.domain.customer.Customer;
import com.jbnu.cocofarm.domain.product.ProductDetail;

import lombok.Data;
import lombok.NoArgsConstructor;

public class CartDto {

    @Data
    @NoArgsConstructor
    public static class CartRegisterDto {

        private int quantity;
        private Customer customer;
        private ProductDetail productDetail;

        public Cart toEntity() {
            return Cart.builder().quantity(quantity).customer(customer).productDetail(productDetail).build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class CartDisplayDto {
        private Long id;
        private String productName;
        private int quantity;
    }
}