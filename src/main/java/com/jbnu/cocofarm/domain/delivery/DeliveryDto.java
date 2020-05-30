package com.jbnu.cocofarm.domain.delivery;

import com.jbnu.cocofarm.domain.domainAssistance.OrderStatus;
import com.jbnu.cocofarm.domain.order.OrderProduct;

import lombok.Data;
import lombok.NoArgsConstructor;

public class DeliveryDto {

    @Data
    @NoArgsConstructor
    public static class DeliveryRegisterDto {

        private String courierCode;
        private String trackingNumber;
        private OrderStatus deliveryStatus;
        private OrderProduct orderProduct;

        public Delivery toEntity() {
            return Delivery.builder().courierCode(courierCode).trackingNumber(trackingNumber)
                    .deliveryStatus(deliveryStatus).orderProduct(orderProduct).build();
        }
    }

}