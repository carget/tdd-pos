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
    public static Coin[] NegativeAndZeroCoinsValues() {
        return new Coin[]{new Coin(0), new Coin(-1), new Coin(-Integer.MIN_VALUE)};
    }

    @DataPoints(value = "ValidCoins")
    public static Coin[] ValidCoinsValues() {
        return new Coin[]{new Coin(1), new Coin(5), new Coin(10), new Coin(25), new Coin(50)};
    }

    @DataPoints(value = "InvalidCoins")
    public static Coin[] InvalidCoinsValues() {
        return new Coin[]{new Coin(2), new Coin(3), new Coin(15), new Coin(75), new Coin(100)};
    }

    @Before
    public void setUp() throws Exception {
        pos = new Pos();
    }

    @Theory
    public void insertCoin() {
        pos.insertCoin(new Coin(5));
        assertThat(pos.getDeposit(), is(5));
    }

    @Test(expected = IllegalArgumentException.class)
    @Theory
    public void negativeCoinTest(@FromDataPoints("NegativeAndZeroCoins") Coin coin) {
        pos.insertCoin(coin);
    }

    @Theory
    public void validCoins(@FromDataPoints("ValidCoins") Coin coin) {
        pos.insertCoin(coin);
        assertThat(pos.getDeposit(), is(coin.getValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    @Theory
    public void invalidCoins(@FromDataPoints("InvalidCoins") Coin coin) {
        pos.insertCoin(coin);
    }

    @Theory
    public void sumCoins() {
        Coin coin = new Coin(1);
        pos.insertCoin(coin);
        pos.insertCoin(coin);
        pos.insertCoin(coin);
        assertThat(pos.getDeposit(), is(3));
    }

    @Theory
    public void addProductToBasket() {
        pos.addProduct("Tea");
        assertThat(pos.getBasket().get("Tea"), is(1));
        pos.addProduct("Tea");
        pos.addProduct("Tea");
        assertThat(pos.getBasket().get("Tea"), is(3));
    }


}
