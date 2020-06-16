package com.jbnu.cocofarm.domain.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jbnu.cocofarm.domain.customer.Customer;
import com.jbnu.cocofarm.util.BaseTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderTotal extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;

    private String customerName;
    private String recipientName;
    private int totalPrice;
    private int deliveryPrice;
    private String contact;
    private String postcode;
    private String address;
    private String detailAddress;
    private String orderMessage;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "orderTotal", cascade = CascadeType.ALL)
    List<OrderProduct> orderProductList = new ArrayList<>();

    @Builder
    public OrderTotal(int totalPrice, int deliveryPrice, String customerName, String recipientName, String contact,
            String postcode, String address, String detailAddress, String orderMessage, Customer customer) {
        this.totalPrice = totalPrice;
        this.deliveryPrice = deliveryPrice;
        this.customerName = customerName;
        this.recipientName = recipientName;
        this.contact = contact;
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.orderMessage = orderMessage;
        this.customer = customer;
    }

}