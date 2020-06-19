package com.jbnu.cocofarm.domain.order.dto;

import java.time.LocalDateTime;

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
        private LocalDateTime createdTimestamp;

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

        public OrderTotalDisplayDto (OrderTotal orderTotal){

            this.customerName = orderTotal.getCustomerName();
            this.recipientName = orderTotal.getRecipientName();
            this.totalPrice = orderTotal.getTotalPrice();
            this.contact = orderTotal.getContact();
            this.postcode = orderTotal.getPostcode();
            this.address = orderTotal.getAddress();
            this.detailAddress = orderTotal.getDetailAddress();
        }

    }
}