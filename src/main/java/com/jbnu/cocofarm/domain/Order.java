package com.jbnu.cocofarm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jbnu.cocofarm.domain.asisstant.OrderStatus;

/**
 * Order
 */
@Entity
public class Order {
    // userId, productId는 외래키로 받아야 함. 다른 엔티티(도메인)를 참조해야 함. 추후에 작업
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String userId;

    @Column
    private Long productId;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(updatable = false)
    private Date createTimestamp;

    @Column(updatable = true)
    private Date updateTimestamp;
}
