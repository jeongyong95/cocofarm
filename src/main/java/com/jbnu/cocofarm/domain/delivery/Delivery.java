package com.jbnu.cocofarm.domain.delivery;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jbnu.cocofarm.domain.domainAssistance.OrderStatus;
import com.jbnu.cocofarm.domain.order.OrderProduct;
import com.jbnu.cocofarm.util.BaseTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;
    private String courierCode;
    private String trackingNumber;

    @Enumerated
    private OrderStatus deliveryStatus;

    @OneToOne(fetch = FetchType.LAZY)
    private OrderProduct orderProduct;

    @Builder
    public Delivery(String courierCode, String trackingNumber, OrderStatus deliveryStatus, OrderProduct orderProduct) {
        this.courierCode = courierCode;
        this.trackingNumber = trackingNumber;
        this.deliveryStatus = deliveryStatus;
        this.orderProduct = orderProduct;
    }
}