package com.jbnu.cocofarm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Basket
 */

@Entity
public class Basket {
    // userId, productId는 외래키로 받아야 함. 다른 엔티티(도메인)를 참조해야 함. 추후에 작업
    @Id
    @Column
    private String userId;

    @Column
    private long productId;

    @Column
    private Long count;

    @Column(updatable = false)
    private Date createTimestamp;

    @Column(updatable = true)
    private Date updateTimestamp;
}