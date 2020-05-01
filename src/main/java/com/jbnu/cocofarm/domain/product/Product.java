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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String imageUrl;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    @JsonManagedReference
    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private ProductDetail productDetail;

    @Column
    private Integer code;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<Review> reviewList = new ArrayList<>();
}