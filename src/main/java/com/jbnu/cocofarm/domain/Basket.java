package com.jbnu.cocofarm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class Basket {
    // userId, productId는 외래키로 받아야 함. 다른 엔티티(도메인)를 참조해야 함. 추후에 작업
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long count;

    @Column(updatable = false)
    private Date createTimestamp;

    @Column(updatable = true)
    private Date updateTimestamp;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "product_id")
    private Product product;
}