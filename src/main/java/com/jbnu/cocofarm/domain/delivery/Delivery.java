package com.jbnu.cocofarm.domain.delivery;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.jbnu.cocofarm.domain.customer.Customer;
import com.jbnu.cocofarm.util.BaseTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;

    private String postcode;
    private String address;
    private String detailAddress;
    private boolean defaultCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Builder
    public Delivery(String postcode, String address, String detailAddress, boolean defaultCheck, Customer customer) {
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.defaultCheck = defaultCheck;
        this.customer = customer;
    }
}