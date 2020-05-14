package com.jbnu.cocofarm.domain.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jbnu.cocofarm.domain.category.Category;
import com.jbnu.cocofarm.domain.review.Review;
import com.jbnu.cocofarm.domain.seller.Seller;
import com.jbnu.cocofarm.domain.utility.BaseTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = false)
    private Integer price;

    @Column
    private Integer code;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @JsonManagedReference
    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private ProductDetail productDetail;

    @Builder
    public Product(String name, String description, String imageUrl, int price, int code, Seller seller,
            Category category, ProductDetail productDetail) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.code = code;
        this.seller = seller;
        this.category = category;
        this.productDetail = productDetail;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<Review> reviewList = new ArrayList<>();
}