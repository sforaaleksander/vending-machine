package com.codecool.vendingmachine;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CoinTest {

//    TEN_GROSZ(16.50, 2.51),
//    TWENTY_GROSZ(18.50, 3.22),
//    FIFTY_GROSZ(20.50, 3.94),
//    ONE_ZLOTY(23.00, 5.00),
//    TWO_ZLOTY(21.50, 5.21),
//    FIVE_ZLOTY(24.00, 6.54);

    @Test
    public void should_ReturnRightCoin_when_PassCorrectValues(){
        assertAll("Should return right coins when pass correct values",
                ()->assertEquals(Coin.TEN_GROSZ, Coin.getCoinByProperties(16.50, 2.51)),
                ()->assertEquals(Coin.TWENTY_GROSZ, Coin.getCoinByProperties(18.50, 3.22)),
                ()->assertEquals(Coin.FIFTY_GROSZ, Coin.getCoinByProperties(20.50, 3.94)),
                ()->assertEquals(Coin.ONE_ZLOTY, Coin.getCoinByProperties(23.00, 5.00)),
                ()->assertEquals(Coin.TWO_ZLOTY, Coin.getCoinByProperties(21.50, 5.21)),
                ()->assertEquals(Coin.FIVE_ZLOTY, Coin.getCoinByProperties(24.00, 6.54))
        );
    }

    @Test
    public void should_ReturnTenGrosz_When_PassCorrectValues() {
        Assert.assertEquals(Coin.TEN_GROSZ, Coin.getCoinByProperties(16.50, 2.51));
    }
}