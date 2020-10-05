package com.codecool.vendingmachine;

public enum Product {
    MARS(1, "Mars", 3.30),
    PRETZEL(2, "Pretzel", 1.80),
    SPRING_WATER(3, "Spring water", 2.10),
    CHUPA_CHUPA(4, "Chupa-chups", 0.90),
    ORANGE_JUICE(5, "Orange juice", 3.70);

    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
