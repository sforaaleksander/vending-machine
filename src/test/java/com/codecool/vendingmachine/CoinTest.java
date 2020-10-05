package com.codecool.vendingmachine;

import org.junit.Assert;
import org.junit.Test;


public class CoinTest {

    @Test
    public void should_ReturnTenGrosz_When_PassCorrectValues() {
        Assert.assertEquals(Coin.TEN_GROSZ, Coin.getCoinByProperties(16.50, 2.51));
    }
}