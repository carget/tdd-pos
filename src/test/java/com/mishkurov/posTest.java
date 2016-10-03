package com.mishkurov;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.rules.ExpectedException;
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

//    @Test(expected = IllegalArgumentException.class)
    @Theory
    public void negativeCoinTest(@FromDataPoints("NegativeAndZeroCoins") int coinValue ) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Coin value must be positive");

        pos.acceptCoin(-1);
    }
}
