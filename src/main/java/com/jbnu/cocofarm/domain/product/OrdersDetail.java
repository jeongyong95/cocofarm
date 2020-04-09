package com.jbnu.cocofarm.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.jbnu.cocofarm.domain.user.Orders;
import com.jbnu.cocofarm.domain.utility.OrdersDetailId;

@Entity
@IdClass(OrdersDetailId.class)
public class OrdersDetail {

    @Column
    private Integer quantity;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDetail productDetail;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Orders orders;
}