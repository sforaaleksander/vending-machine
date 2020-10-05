package com.codecool.vendingmachine;

import java.math.BigDecimal;

public enum Product {
    MARS(1, "Mars", new BigDecimal("3.30")),
    PRETZEL(2, "Pretzel", new BigDecimal("1.80")),
    SPRING_WATER(3, "Spring water", new BigDecimal("2.10")),
    CHUPA_CHUPA(4, "Chupa-chups", new BigDecimal("0.90")),
    ORANGE_JUICE(5, "Orange juice", new BigDecimal("3.70"));

    int id;
    String name;
    BigDecimal price;

    Product(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
