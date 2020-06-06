package com.jbnu.cocofarm.domain.seller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductQuestion;
import com.jbnu.cocofarm.util.BaseTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Seller extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;
    private String sellerCode;
    private String password;
    private String name;
    private String contact;
    private String account;

    @Builder
    public Seller(String sellerCode, String password, String name, String contact, String account) {
        this.sellerCode = sellerCode;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.account = account;
    }

    @OneToMany(mappedBy = "seller")
    List<Product> productList = new ArrayList<>();

    
    @OneToMany(mappedBy = "seller")
    List<ProductQuestion> productQuestionList = new ArrayList<>();
}