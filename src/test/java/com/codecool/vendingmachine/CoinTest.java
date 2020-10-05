package com.codecool.vendingmachine;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class CoinTest {

    @Test
    public void should_ReturnRightCoin_when_PassCorrectValues(){
        assertAll("Should return right coins when pass correct values",
                ()->assertEquals(Coin.FIVE_GROSZ, Coin.getCoinByProperties(new BigDecimal("19.50"),new BigDecimal("2.59"))),
                ()->assertEquals(Coin.TEN_GROSZ, Coin.getCoinByProperties(new BigDecimal("16.50"), new BigDecimal("2.51"))),
                ()->assertEquals(Coin.TWENTY_GROSZ, Coin.getCoinByProperties(new BigDecimal("18.50"), new BigDecimal("3.22"))),
                ()->assertEquals(Coin.FIFTY_GROSZ, Coin.getCoinByProperties(new BigDecimal("20.50"), new BigDecimal("3.94"))),
                ()->assertEquals(Coin.ONE_ZLOTY, Coin.getCoinByProperties(new BigDecimal("23.00"), new BigDecimal("5.00"))),
                ()->assertEquals(Coin.TWO_ZLOTY, Coin.getCoinByProperties(new BigDecimal("21.50"), new BigDecimal("5.21"))),
                ()->assertEquals(Coin.FIVE_ZLOTY, Coin.getCoinByProperties(new BigDecimal("24.00"), new BigDecimal("6.54")))
        );
    }

    @Test
    public void should_ThrowException_when_PassedBothWrongValues(){
        assertThrows(IllegalArgumentException.class, ()->Coin.getCoinByProperties(new BigDecimal("1"), new BigDecimal("2")));
    }

    @Test
    public void should_ThrowException_when_PassedSingleWrongValue(){
        assertThrows(IllegalArgumentException.class, ()->Coin.getCoinByProperties(new BigDecimal("16.50"), new BigDecimal("0")));
    }

    @Test
    public void should_ThrowException_when_PassedMissMatchedValues(){
        assertThrows(IllegalArgumentException.class, ()->Coin.getCoinByProperties(new BigDecimal("16.50"), new BigDecimal("3.22")));
    }
}