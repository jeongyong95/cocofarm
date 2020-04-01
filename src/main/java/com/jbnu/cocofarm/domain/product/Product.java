package com.jbnu.cocofarm.domain.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    // categoryId는 외래키로 받을 거임. 추후에 작업함
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = true)
    private Long discount;

    @Column(nullable = false)
    private Long stock;

    @Column(updatable = false)
    private Date createTimestamp;

    @Column(updatable = true)
    private Date updateTimestamp;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}