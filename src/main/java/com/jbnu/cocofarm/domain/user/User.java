package com.jbnu.cocofarm.domain.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jbnu.cocofarm.domain.basket.Basket;
import com.jbnu.cocofarm.domain.orders.Orders;
import com.jbnu.cocofarm.domain.orders.OrdersTemp;
import com.jbnu.cocofarm.domain.utility.BaseTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 100, nullable = false, updatable = false)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = true)
    private String postcode;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String detailAddress;

    @Builder
    public User(String email, String password, String name, String contact, String postcode, String address,
            String detailAddress) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Orders> ordersList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Basket> basketList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<OrdersTemp> ordersTempList = new ArrayList<>();

}