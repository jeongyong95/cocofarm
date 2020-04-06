package com.jbnu.cocofarm.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.jbnu.cocofarm.domain.user.Orders;

@Entity
@IdClass(OrdersDetailId.class)
public class OrdersDetail {

    @Column
    private Integer quantity;

    @Id
    @ManyToOne
    private ProductDetail productDetail;

    @Id
    @ManyToOne
    private Orders orders;
}