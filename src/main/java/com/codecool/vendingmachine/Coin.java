package com.codecool.vendingmachine;

public enum Coin {
    TEN_GROSZ(16.50, 2.51),
    TWENTY_GROSZ(18.50, 3.22),
    FIFTY_GROSZ(20.50, 3.94),
    ONE_ZLOTY(23.00, 5.00),
    TWO_ZLOTY(21.50, 5.21),
    FIVE_ZLOTY(24.00, 6.54);

    double dimensions;
    double weight;
    Coin(double dimensions, double weight) {
        this.dimensions = dimensions;
        this.weight = weight;
    }

    public static Coin getCoinByProperties(double dimensions, double weight) {
        for (Coin coin : Coin.values()) {
            if (coin.weight == weight && coin.dimensions == dimensions) {
                return coin;
            }
        }
        throw new IllegalArgumentException("Machine could not recognize the coin");
    }
}
