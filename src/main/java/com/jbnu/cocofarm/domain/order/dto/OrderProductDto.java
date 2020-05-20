package com.jbnu.cocofarm.domain.order.dto;

import com.jbnu.cocofarm.domain.order.OrderProduct;
import com.jbnu.cocofarm.domain.order.OrderTotal;
import com.jbnu.cocofarm.domain.product.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

public class OrderProductDto {

    @Data
    @NoArgsConstructor
    public static class OrderProductRegisterDto {

        private Long productId;
        private String productName;
        private int quantity;
        private int price;
        private int productTotalPrice;
        private boolean orderCheck;
        private Product product;
        private OrderTotal orderTotal;

        public OrderProduct toEntity() {
            return OrderProduct.builder().quantity(quantity).productTotalPrice(productTotalPrice).orderCheck(orderCheck)
                    .product(product).orderTotal(orderTotal).build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class OrderProductDisplayDto {
        private Long productId;
        private String productName;
        private int quantity;
        private int price;
        private int ProductTotalPrice;
    }
}