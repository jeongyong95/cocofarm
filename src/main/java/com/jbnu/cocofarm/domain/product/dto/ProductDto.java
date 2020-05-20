package com.jbnu.cocofarm.domain.product.dto;

import java.time.LocalDateTime;

import com.jbnu.cocofarm.domain.domainAssistance.Category;
import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.seller.Seller;

import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductDto {

    @Data
    @NoArgsConstructor
    public static class ProductRegisterDto {

        private String name;
        private String description;
        private Category category;
        private String imageUrl;
        private int price;
        private LocalDateTime deadline;
        private ProductDetail productDetail;
        private Seller seller;

        public Product toEntity() {
            return Product.builder().name(name).description(description).category(category).imageUrl(imageUrl)
                    .price(price).deadline(deadline).productDetail(productDetail).seller(seller).build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class ProductUpdateDto {

    }

    @Data
    @NoArgsConstructor
    public static class ProductDisplayDto {
        private Long id;
        private String name;
        private String description;
        private Category category;
        private String imageUrl;
        private int price;
        private int stock;
        private LocalDateTime deadline;

        public ProductDisplayDto(Product product) {
            this.id = product.getId();
            this.name = product.getName();
            this.description = product.getDescription();
            this.category = product.getCategory();
            this.imageUrl = product.getImageUrl();
            this.price = product.getPrice();
            this.stock = product.getProductDetail().getStock();
            this.deadline = product.getDeadline();
        }
    }
}