package com.jbnu.cocofarm.domain.order;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.jbnu.cocofarm.domain.delivery.Delivery;
import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.util.BaseTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProduct extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;
    private int quantity;
    private int productTotalPrice;
    private boolean orderCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderTotal orderTotal;

    @OneToOne(mappedBy = "orderProduct", cascade = CascadeType.ALL)
    private Delivery delivery;

    @Builder
    public OrderProduct(int quantity, int productTotalPrice, boolean orderCheck, Product product,
            OrderTotal orderTotal) {
        this.quantity = quantity;
        this.productTotalPrice = productTotalPrice;
        this.orderCheck = orderCheck;
        this.product = product;
        this.orderTotal = orderTotal;
    }
}