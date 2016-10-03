package com.mishkurov;

public class Pos {

    private int deposit;

    public Pos() {
        this.deposit = 0;
    }

    public void acceptCoin(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Coin value must be positive");
        }
        deposit += i;
    }

    public int getDeposit() {
        return deposit;
    }
}
