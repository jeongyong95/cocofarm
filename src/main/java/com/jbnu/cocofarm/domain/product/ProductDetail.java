package com.jbnu.cocofarm.domain.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer stockNumber;

    @OneToOne
    private Product product;

    @OneToMany(mappedBy = "productDetail")
    List<OrderDetail> orderDetailList = new ArrayList<>();
}