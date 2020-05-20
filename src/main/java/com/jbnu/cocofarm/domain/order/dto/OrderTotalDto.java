package com.jbnu.cocofarm.domain.order.dto;

import com.jbnu.cocofarm.domain.customer.Customer;
import com.jbnu.cocofarm.domain.order.OrderTotal;

import lombok.Data;
import lombok.NoArgsConstructor;

public class OrderTotalDto {

    @Data
    @NoArgsConstructor
    public static class OrderTotalRegisterDto {

        private String customerName;
        private String recipientName;
        private int totalPrice;
        private int deliveryPrice;
        private String contact;
        private String postcode;
        private String address;
        private String detailAddress;
        private String orderMessage;
        private Customer customer;

        public OrderTotal toEntity() {
            return OrderTotal.builder().customerName(customerName).recipientName(recipientName).contact(contact)
                    .totalPrice(totalPrice).deliveryPrice(deliveryPrice).postcode(postcode).address(address)
                    .detailAddress(detailAddress).orderMessage(orderMessage).customer(customer).build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class OrderTotalDisplayDto {

        private String customerName;
        private String recipientName;
        private int totalPrice;
        private int deliveryPrice;
        private String contact;
        private String postcode;
        private String address;
        private String detailAddress;

    }
}