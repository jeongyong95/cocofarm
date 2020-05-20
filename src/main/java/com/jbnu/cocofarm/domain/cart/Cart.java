package com.jbnu.cocofarm.domain.cart;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.jbnu.cocofarm.domain.customer.Customer;
import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.util.BaseTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cart extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDetail productDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Builder
    public Cart(int quantity, ProductDetail productDetail, Customer customer) {
        this.quantity = quantity;
        this.productDetail = productDetail;
        this.customer = customer;
    }

}