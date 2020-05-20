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
public class ProductQuestion extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;
    private String question;
    private String answer;
    private boolean replyState;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDetail productDetail;

    @Builder
    public ProductQuestion(String question, String answer, boolean replyState, Customer customer,
            ProductDetail productDetail) {
        this.question = question;
        this.answer = answer;
        this.replyState = replyState;
        this.customer = customer;
        this.productDetail = productDetail;
    }
}