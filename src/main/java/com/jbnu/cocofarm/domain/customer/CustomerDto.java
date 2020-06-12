package com.jbnu.cocofarm.domain.customer;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

public class CustomerDto {

    @Data
    @NoArgsConstructor
    public static class CustomerLoginDto {
        @NotBlank(message = "이메일을 입력하세요!")
        private String email;
        @NotBlank(message = "비밀번호를 입력하세요!")
        private String password;
    }

    @Data
    @NoArgsConstructor
    public static class CustomerSessionDto {
        private Long id;
        private String name;

        public CustomerSessionDto(Customer customer) {
            this.id = customer.getId();
            this.name = customer.getName();
        }
    }

    @Data
    @NoArgsConstructor
    public static class CustomerRegisterDto {

        @NotBlank(message = "이름을 입력하세요!")
        private String name;
        @NotBlank(message = "이메일을 입력하세요!")
        private String email;
        @NotBlank(message = "비밀번호를 입력하세요!")
        private String password;
        @NotBlank(message = "전화번호를 입력하세요!")
        private String contact;
        @NotBlank(message = "우편번호를 입력하세요!")
        private String postcode;
        @NotBlank(message = "주소를 입력하세요!")
        private String address;
        private String detailAddress;

        public Customer toEntity() {
            return Customer.builder().name(name).email(email).password(password).contact(contact).postcode(postcode)
                    .address(address).detailAddress(detailAddress).build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class CustomerUpdateDto {
        private String name;
        private String password;
        private String contact;
        private String postcode;
        private String address;
        private String detailAddress;

        public CustomerUpdateDto(Customer customer) {
            this.name = customer.getName();
            this.password = customer.getPassword();
            this.contact = customer.getContact();
            this.postcode = customer.getPostcode();
            this.address = customer.getAddress();
            this.detailAddress = customer.getDetailAddress();
        }
    }
}