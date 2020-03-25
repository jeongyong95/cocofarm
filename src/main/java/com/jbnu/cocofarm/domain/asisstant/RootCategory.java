package com.jbnu.cocofarm.domain.asisstant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RootCategory {
    agriculturalProducts(1, "농산물"), processedFood(2, "가공식품");

    private Integer id;
    private String name;

}