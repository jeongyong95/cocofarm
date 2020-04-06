package com.jbnu.cocofarm.domain.product;

import java.io.Serializable;

public class OrdersDetailId implements Serializable {

    private Long productDetail;
    private Long orders;

    public OrdersDetailId() {

    }

    public OrdersDetailId(Long productDetail, Long orders) {
        this.productDetail = productDetail;
        this.orders = orders;
    }
}