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

import com.jbnu.cocofarm.domain.user.Basket;

@Entity
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer stockNumber;

    @OneToOne(mappedBy = "productDetail")
    private Product product;

    @OneToMany(mappedBy = "productDetail")
    List<Basket> basketList = new ArrayList<>();

    @OneToMany(mappedBy = "productDetail")
    List<OrderDetail> orderDetailList = new ArrayList<>();
}