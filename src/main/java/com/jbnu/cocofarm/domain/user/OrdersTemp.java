package com.jbnu.cocofarm.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jbnu.cocofarm.domain.product.ProductDetail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrdersTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer count;

    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDetail productDetail;

    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
}