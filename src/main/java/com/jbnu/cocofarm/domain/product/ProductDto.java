package com.jbnu.cocofarm.domain.product;

import com.jbnu.cocofarm.domain.category.Category;
import com.jbnu.cocofarm.domain.seller.Seller;

import lombok.Data;

public class ProductDto {
    @Data
    public class ProductRegisterDto {

        private String name;
        private String description;
        private String imageUrl;
        private int price;
        private int code;
        private Seller seller;
        private Category category;
        private ProductDetail productDetail;

        public Product toEntity() {
            return Product.builder().name(name).description(description).imageUrl(imageUrl).price(price).code(code)
                    .seller(seller).category(category).productDetail(productDetail).build();
        }

    }

    @Data
    public class ProductUpdateDto {

        private Long id;
        private String name;
        private String description;
        private String imageUrl;
        private int price;
        private int code;
        private Seller seller;
        private Category category;
        private ProductDetail productDetail;

        public Product toEntity() {
            return Product.builder().name(name).description(description).imageUrl(imageUrl).price(price).code(code)
                    .seller(seller).category(category).productDetail(productDetail).build();
        }

    }

    @Data
    public class ProductResponseDto {

        private Long id;
        private String name;
        private String description;
        private String imageUrl;
        private int price;
        private int code;
        private Seller seller;
        private Category category;
        private ProductDetail productDetail;

        public ProductResponseDto(Product product) {
            this.id = product.getId();
            this.name = product.getName();
            this.description = product.getDescription();
            this.imageUrl = product.getImageUrl();
            this.price = product.getPrice();
            this.code = product.getCode();
            this.seller = product.getSeller();
            this.category = product.getCategory();
            this.productDetail = product.getProductDetail();
        }
    }
}