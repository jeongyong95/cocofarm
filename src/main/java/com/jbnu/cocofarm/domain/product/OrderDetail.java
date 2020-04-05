package com.jbnu.cocofarm.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.jbnu.cocofarm.domain.user.Order;

@Entity
@IdClass(OrderDetailId.class)
public class OrderDetail {

    @Column
    private Integer quantity;

    @Id
    @ManyToOne
    private ProductDetail productDetail;

    @Id
    @ManyToOne
    private Order order;
}