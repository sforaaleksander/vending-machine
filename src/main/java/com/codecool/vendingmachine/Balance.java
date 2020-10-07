package com.codecool.vendingmachine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Balance {
    private BigDecimal inserted;
    private BigDecimal required;
    private Map<Coin, Integer> coins;

    public Balance() {
        this.inserted = new BigDecimal("0");
        this.coins = new HashMap<>();
        populateMap();
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }

    private void populateMap() {
        coins.put(Coin.FIVE_GROSZ, 0);
        coins.put(Coin.TEN_GROSZ, 0);
        coins.put(Coin.TWENTY_GROSZ, 3);
        coins.put(Coin.FIFTY_GROSZ, 1);
        coins.put(Coin.ONE_ZLOTY, 0);
        coins.put(Coin.TWO_ZLOTY, 3);
        coins.put(Coin.FIVE_ZLOTY, 1);
    }

    public BigDecimal getInserted() {
        return inserted;
    }

    public void insert(BigDecimal inserted) {
        this.inserted = this.inserted.add(inserted);
    }

    public BigDecimal getRequired() {
        return required;
    }

    public void setRequired(BigDecimal required) {
        this.required = required;
    }

    public boolean isPaid(){
        return inserted.compareTo(required) >= 0;
    }
}
