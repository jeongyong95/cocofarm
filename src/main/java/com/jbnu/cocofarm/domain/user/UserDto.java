package com.jbnu.cocofarm.domain.user;

import lombok.Builder;
import lombok.Data;

public class UserDto {

    @Data
    public static class UserRegisterDto {
        private String email;
        private String password;
        private String name;
        private String contact;
        private String postcode;
        private String address;
        private String detailAddress;

        @Builder
        public UserRegisterDto(String email, String password, String name, String contact, String postcode,
                String address, String detailAddress) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.contact = contact;
            this.postcode = postcode;
            this.address = address;
            this.detailAddress = detailAddress;
        }

        public User toEntity() {
            return User.builder().email(email).password(password).name(name).contact(contact).postcode(postcode)
                    .address(address).detailAddress(detailAddress).build();
        }
    }

    @Data
    public static class UserUpdateDto {
        private String email;
        private String password;
        private String name;
        private String contact;
        private String postcode;
        private String address;
        private String detailAddress;

        @Builder
        public UserUpdateDto(String email, String password, String name, String contact, String postcode,
                String address, String detailAddress) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.contact = contact;
            this.postcode = postcode;
            this.address = address;
            this.detailAddress = detailAddress;
        }

        public User toEntity() {
            return User.builder().email(email).password(password).name(name).contact(contact).postcode(postcode)
                    .address(address).detailAddress(detailAddress).build();
        }
    }

    @Data
    public class UserResponseDto {

        private Long id;

        private String email;

        private String name;

        private String contact;

        private String postcode;

        private String address;

        private String detailAddress;

        public UserResponseDto(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.contact = user.getContact();
            this.postcode = user.getPostcode();
            this.address = user.getAddress();
            this.detailAddress = user.getDetailAddress();
        }
    }
}