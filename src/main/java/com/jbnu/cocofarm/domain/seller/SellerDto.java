package com.jbnu.cocofarm.domain.seller;

import lombok.Data;
import lombok.NoArgsConstructor;

public class SellerDto {

    @Data
    @NoArgsConstructor
    public static class SellerLoginDto {
        private String sellerCode;
        private String password;
    }

    @Data
    @NoArgsConstructor
    public static class SellerRegisterDto {

        private String sellerCode;
        private String password;
        private String name;
        private String contact;
        private String account;

        public Seller toEntity() {
            return Seller.builder().sellerCode(sellerCode).password(password).name(name).contact(contact)
                    .account(account).build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class SellerSessionDto {

        private Long id;
        private String name;

        public SellerSessionDto(Seller seller) {
            this.id = seller.getId();
            this.name = seller.getName();
        }
    }
}