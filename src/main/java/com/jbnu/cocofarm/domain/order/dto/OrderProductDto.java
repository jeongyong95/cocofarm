package com.jbnu.cocofarm.domain.order.dto;

import java.time.LocalDateTime;

import com.jbnu.cocofarm.domain.delivery.Delivery;
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

        private Long id;
        private Long productId;
        private String productName;
        private int quantity;
        private int price;
        private int ProductTotalPrice;
        private LocalDateTime createdTimestamp;
        private OrderTotal orderTotal;
        private Delivery delivery;

        public OrderProductDisplayDto(OrderProduct orderProduct) {
            
            this.id = orderProduct.getId();
            this.productId = orderProduct.getId();
            this.productName = orderProduct.getProduct().getName();
            this.quantity = orderProduct.getQuantity();
            this.ProductTotalPrice = orderProduct.getProductTotalPrice();
            this.orderTotal = orderProduct.getOrderTotal();
            this.delivery = orderProduct.getDelivery();
        }
    }
}