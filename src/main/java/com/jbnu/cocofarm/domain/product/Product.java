package com.jbnu.cocofarm.domain.product;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.jbnu.cocofarm.domain.domainAssistance.Category;
import com.jbnu.cocofarm.domain.seller.Seller;
import com.jbnu.cocofarm.util.BaseTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends BaseTime {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @Enumerated
    private Category category;
    private String imageUrl;
    private int price;
    private LocalDateTime deadline;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProductDetail productDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    @Builder
    public Product(String name, String description, Category category, String imageUrl, int price,
            LocalDateTime deadline, ProductDetail productDetail, Seller seller) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.price = price;
        this.deadline = deadline;
        this.productDetail = productDetail;
        this.seller = seller;
    }
}