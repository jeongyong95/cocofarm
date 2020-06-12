package com.jbnu.cocofarm.domain.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jbnu.cocofarm.domain.product.dto.ProductDetailDto.DetailUpdateDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductDetail {

    @Id
    @GeneratedValue
    private Long id;
    private int stock;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL)
    List<ProductReview> productReviewList = new ArrayList<>();

    @Builder
    public ProductDetail(int stock, Product product) {
        this.stock = stock;
        this.product = product;
    }

    public void updateDetail(DetailUpdateDto updateDto) {
        this.stock = updateDto.getStock();
    }

}