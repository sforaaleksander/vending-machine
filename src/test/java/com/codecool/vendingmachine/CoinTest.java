package com.codecool.vendingmachine;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CoinTest {

    @Test
    public void should_ReturnRightCoin_when_PassCorrectValues(){
        assertAll("Should return right coins when pass correct values",
                ()->assertEquals(Coin.FIVE_GROSZ, Coin.getCoinByProperties(19.50,2.59)),
                ()->assertEquals(Coin.TEN_GROSZ, Coin.getCoinByProperties(16.50, 2.51)),
                ()->assertEquals(Coin.TWENTY_GROSZ, Coin.getCoinByProperties(18.50, 3.22)),
                ()->assertEquals(Coin.FIFTY_GROSZ, Coin.getCoinByProperties(20.50, 3.94)),
                ()->assertEquals(Coin.ONE_ZLOTY, Coin.getCoinByProperties(23.00, 5.00)),
                ()->assertEquals(Coin.TWO_ZLOTY, Coin.getCoinByProperties(21.50, 5.21)),
                ()->assertEquals(Coin.FIVE_ZLOTY, Coin.getCoinByProperties(24.00, 6.54))
        );
    }

    @Test
    public void should_ThrowException_when_PassedBothWrongValues(){
        assertThrows(IllegalArgumentException.class, ()->Coin.getCoinByProperties(1, 2));
    }

    @Test
    public void should_ThrowException_when_PassedSingleWrongValue(){
        assertThrows(IllegalArgumentException.class, ()->Coin.getCoinByProperties(16.50, 0));
    }

    @Test
    public void should_ThrowException_when_PassedMissMatchedValues(){
        assertThrows(IllegalArgumentException.class, ()->Coin.getCoinByProperties(16.50, 3.22));
    }
}