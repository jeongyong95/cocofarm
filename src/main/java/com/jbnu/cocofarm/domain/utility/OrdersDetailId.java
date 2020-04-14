package com.jbnu.cocofarm.domain.utility;

import java.io.Serializable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrdersDetailId implements Serializable {

    private Long productDetail;
    private Long orders;

    public OrdersDetailId() {

    }

}