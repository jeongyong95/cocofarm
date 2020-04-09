package com.jbnu.cocofarm.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.utility.BaseTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Basket
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Basket extends BaseTime {
    // userId, productId는 외래키로 받아야 함. 다른 엔티티(도메인)를 참조해야 함. 추후에 작업
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDetail productDetail;

}