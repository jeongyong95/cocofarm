package com.jbnu.cocofarm.domain.asisstant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    admin(1, "관리자"), buyer(2, "구매자"), seller(3, "판매자");

    private Integer id;
    private String name;
}