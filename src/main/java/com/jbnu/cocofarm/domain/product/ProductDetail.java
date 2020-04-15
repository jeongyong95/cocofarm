package com.jbnu.cocofarm.domain.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jbnu.cocofarm.domain.user.Basket;
import com.jbnu.cocofarm.domain.user.OrdersDetail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer stockNumber;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    @JsonManagedReference
    @OneToMany(mappedBy = "productDetail", fetch = FetchType.LAZY)
    List<Basket> basketList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "productDetail", fetch = FetchType.LAZY)
    List<OrdersDetail> ordersDetailList = new ArrayList<>();
}