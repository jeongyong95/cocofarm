package com.jbnu.cocofarm.domain.orders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.utility.OrdersDetailId;

@Entity
@IdClass(OrdersDetailId.class)
public class OrdersDetail {

    @Column
    private Integer count;

    @Id
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDetail productDetail;

    @Id
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Orders orders;

}