package com.codecool.vendingmachine.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Coin {
    FIVE_GROSZ(new BigDecimal("19.50"),new BigDecimal("2.59"), "5GR", new BigDecimal("0.05")),
    TEN_GROSZ(new BigDecimal("16.50"), new BigDecimal("2.51"), "10GR", new BigDecimal("0.10")),
    TWENTY_GROSZ(new BigDecimal("18.50"), new BigDecimal("3.22"), "20GR", new BigDecimal("0.20")),
    FIFTY_GROSZ(new BigDecimal("20.50"), new BigDecimal("3.94"), "50GR", new BigDecimal("0.50")),
    ONE_ZLOTY(new BigDecimal("23.00"), new BigDecimal("5.00"), "1ZL", new BigDecimal("1.0")),
    TWO_ZLOTY(new BigDecimal("21.50"), new BigDecimal("5.21"), "2ZL", new BigDecimal("2.0")),
    FIVE_ZLOTY(new BigDecimal("24.00"), new BigDecimal("6.54"), "5ZL", new BigDecimal("5.0"));

    public BigDecimal dimensions;
    public BigDecimal weight;
    public String stringCoin;
    public BigDecimal value;
    static List<String> strings = Arrays.stream(Coin.values()).map(e->e.stringCoin).collect(Collectors.toList());

    Coin(BigDecimal dimensions, BigDecimal weight, String stringCoin, BigDecimal value) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.stringCoin = stringCoin;
        this.value = value;
    }

    public static Coin getCoinByProperties(BigDecimal dimensions, BigDecimal weight) {
        for (Coin coin : Coin.values()) {
            if (coin.weight.equals(weight) && coin.dimensions.equals(dimensions)) {
                return coin;
            }
        }
        throw new IllegalArgumentException("Machine could not recognize the coin");
    }

    public static BigDecimal[] getCoinByString(String string) {
        for (Coin coin : Coin.values()) {
            if (coin.stringCoin.equals(string)) {
                return new BigDecimal[]{coin.dimensions, coin.weight};
            }
        }
        throw new IllegalArgumentException("Machine could not recognize the coin");
    }
}
