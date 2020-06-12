package com.jbnu.cocofarm.domain.product;

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
public class ProductReview extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private int starPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDetail productDetail;

    @Builder
    public ProductReview(String title, String content, int starPoint, Customer customer, ProductDetail productDetail) {
        this.title = title;
        this.content = content;
        this.starPoint = starPoint;
        this.customer = customer;
        this.productDetail = productDetail;
    }
}