package com.jbnu.cocofarm.domain.domainAssistance;

public enum Category {
    AGRICULTURAL(0), AQUATIC(1), INDUSTIRAL(2);

    private int number;

    private Category(int number) {
        this.number = number;
    }
}