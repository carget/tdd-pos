package com.mishkurov;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(Theories.class)
public class posTest {

    private Pos pos;

    @DataPoints(value = "NegativeAndZeroCoins")
    public static int[] NegativeAndZeroCoinsValues() {
        return new int[]{0, -1, -Integer.MIN_VALUE};
    }

    @DataPoints(value = "ValidCoins")
    public static int[] ValidCoinsValues() {
        return new int[]{1, 5, 10, 25, 50};
    }

    @DataPoints(value = "InvalidCoins")
    public static int[] InvalidCoinsValues() {
        return new int[]{2, 3, 15, 75, 100};
    }

    @Before
    public void setUp() throws Exception {
        pos = new Pos();
    }

    @Theory
    public void acceptCoin() {
        pos.acceptCoin(5);
        assertThat(pos.getDeposit(), is(5));
    }

    @Test(expected = IllegalArgumentException.class)
    @Theory
    public void negativeCoinTest(@FromDataPoints("NegativeAndZeroCoins") int coinValue) {
        pos.acceptCoin(coinValue);
    }

    @Theory
    public void validCoins(@FromDataPoints("ValidCoins") int coinValue) {
        pos.acceptCoin(coinValue);
        assertThat(pos.getDeposit(), is(coinValue));
    }

    @Test(expected = IllegalArgumentException.class)
    @Theory
    public void invalidCoins(@FromDataPoints("InvalidCoins") int coinValue) {
        pos.acceptCoin(coinValue);
    }

    @Theory
    public void sumCoins() {
        pos.acceptCoin(1);
        pos.acceptCoin(1);
        pos.acceptCoin(1);
        assertThat(pos.getDeposit(), is(3));
    }
}
