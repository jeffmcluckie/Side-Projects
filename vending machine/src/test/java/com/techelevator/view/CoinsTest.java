package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoinsTest {

    private Coins coins;
    @Before
    public void setup() { coins = new Coins(); }
    @Test
    public void makeChange_returns_expected_quarters() {

        double change = 0.40;
        int expectedResult = 1;
        coins.makeChange(change);
        int actualResult = coins.getCountQuarters();
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void makeChange_returns_expected_dimes() {

        double change = 0.40;
        int expectedResult = 1;
        coins.makeChange(change);
        int actualResult = coins.getCountDimes();
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void makeChange_returns_expected_nickels() {

        double change = 0.40;
        int expectedResult = 1;
        coins.makeChange(change);
        int actualResult = coins.getCountNickels();
        Assert.assertEquals(expectedResult, actualResult);

    }

    // @TODO cover case of 0, 1, 1+ for coin returns

}
