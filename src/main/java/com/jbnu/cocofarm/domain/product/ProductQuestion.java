package com.jbnu.cocofarm.domain.product;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.jbnu.cocofarm.domain.customer.Customer;
import com.jbnu.cocofarm.domain.seller.Seller;
import com.jbnu.cocofarm.util.BaseTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductQuestion extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Long groupNumber;
    private int groupLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;
   
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public ProductQuestion(Long groupNumber, int groupLevel, String title, String content, Customer customer, Seller seller,
             Product product) {
        this.groupNumber = groupNumber;
        this.groupLevel = groupLevel;
        this.title = title;
        this.content = content;
        this.customer = customer;
        this.seller = seller;
        this.product = product;
    }
}