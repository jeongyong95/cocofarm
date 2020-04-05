package com.jbnu.cocofarm.domain.product;

import java.io.Serializable;

public class OrderDetailId implements Serializable {

    private Long productDetail;
    private Long order;

    public OrderDetailId() {

    }

    public OrderDetailId(Long productDetail, Long order) {
        this.productDetail = productDetail;
        this.order = order;
    }
}