package com.jbnu.cocofarm.domain.product.dto;

import java.time.LocalDateTime;

import com.jbnu.cocofarm.domain.customer.Customer;
import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.product.ProductQuestion;
import com.jbnu.cocofarm.domain.seller.Seller;


import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductQuestionDto {

    @Data
    @NoArgsConstructor
    public static class QuestionDto {

        private Long id;
        private Long productId;
        private String title;
        private String content;
        private Customer customer;
        private Long groupNumber;
        private int groupLevel;
        private Product product;
        private LocalDateTime createdTimestamp;

        public ProductQuestion toEntity() {
            return ProductQuestion.builder().title(title).content(content).groupNumber(groupNumber)
                    .groupLevel(groupLevel).customer(customer).product(product).build();
        }

    }

    @Data
    @NoArgsConstructor
    public static class QuestionDisplayDto {

        private Long id;
        private Long productId;
        private String title;
        private String content;
        private Customer customer;
        private Seller seller;
        private Long groupNumber;
        private int groupLevel;
        private LocalDateTime createdTimestamp;


        public QuestionDisplayDto(ProductQuestion productQuestion){
            
            this.id = productQuestion.getId();
            this.productId = productQuestion.getProduct().getId();
            this.title = productQuestion.getTitle();
            this.content = productQuestion.getContent();
            this.customer = productQuestion.getCustomer();
            this.seller = productQuestion.getSeller();
            this.groupNumber = productQuestion.getGroupNumber();
            this.groupLevel = productQuestion.getGroupLevel();
            this.createdTimestamp = productQuestion.getCreatedTimestamp();
        }

    }

    @Data
    @NoArgsConstructor
    public static class AnswerQuestionDto {

        private Long id;
        private Long productId;
        private String title;
        private String content;
        private Seller seller;
        private Long groupNumber;
        private int groupLevel;
        private Product product;
        private LocalDateTime createdTimestamp;

        public ProductQuestion toEntity() {
            return ProductQuestion.builder().title(title).content(content).groupNumber(groupNumber)
                    .groupLevel(groupLevel).seller(seller).product(product).build();
        }

    }

 
}