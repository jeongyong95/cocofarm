package com.jbnu.cocofarm.domain.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.utility.BaseTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Seller extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String sellerCode;

    @Column
    private String password;

    @Column
    private String account;

    @JsonManagedReference
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    List<Product> productList = new ArrayList<>();
}