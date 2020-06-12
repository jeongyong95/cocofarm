package com.jbnu.cocofarm.domain.product.dto;

import java.time.LocalDateTime;

import com.jbnu.cocofarm.domain.customer.Customer;
import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.product.ProductReview;

import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductReviewDto {

    @Data
    @NoArgsConstructor
    public static class ReviewRegisterDto {
        private String title;
        private String content;
        private int starPoint;
        private Customer customer;
        private ProductDetail productDetail;

        public ProductReview toEntity() {
            return ProductReview.builder().title(title).content(content).starPoint(starPoint).customer(customer)
                    .productDetail(productDetail).build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class ReviewDisplayDto {
        private Long detailId;
        private Long reviewId;
        private Long customerId;

        private String title;
        private String content;
        private String customerName;
        private int starPoint;
        private LocalDateTime createdTimestamp;

    }
}