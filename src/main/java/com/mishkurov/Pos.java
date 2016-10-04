package com.mishkurov;

import java.util.Arrays;

public class Pos {

    private int deposit;
    private int[] validCoins = new int[]{1, 5, 10, 25, 50};

    public Pos() {
        this.deposit = 0;
    }

    public void acceptCoin(int coin) {
        if (coin <= 0) {
            throw new IllegalArgumentException("Coin value must be positive");
        }
        for (int validCoin : validCoins) {
            if (coin == validCoin) {
                deposit += coin;
                return;
            }
        }
        throw new IllegalArgumentException("Invalid coin value. Expected one of: " + validCoins);
    }

    public int getDeposit() {
        return deposit;
    }
}
