package com.jbnu.cocofarm.domain.asisstant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    ready(0, "배송준비중", "배송을 준비하고 있습니다."), delivery(1, "배송중", "주문하신 상품이 고객님께 배송중입니다."),
    complete(2, "배송완료", "주문하신 상품의 배송이 완료되었습니다.");

    private Integer id;
    private String title;
    private String description;

}