package com.mishkurov;

import java.util.*;

public class Pos implements PosInt {

    private static final int INITIAL_COIN_QUANTITY = 100;

    private int deposit;
    private Integer[] validCoins = new Integer[]{1, 5, 10, 25, 50};
    private Integer[] validCoinsReverse = new Integer[]{50, 25, 10, 5, 1};
    private Map<Coin, Integer> coinRemainder;
    private Map<String, Integer> basket;

    public Pos() {
        this.deposit = 0;
        basket = new HashMap<>();
        coinRemainder = new HashMap<>();
        for (Coin c : getAllowedCoins()) {
            coinRemainder.put(c, INITIAL_COIN_QUANTITY);
        }
    }

    public void insertCoin(Coin coin) {
        if (coin.getValue() <= 0) {
            throw new IllegalArgumentException("Coin value must be positive");
        }
        for (int validCoin : validCoinsReverse) {
            if (coin.getValue() == validCoin) {
                deposit += coin.getValue();
                return;
            }
        }
        throw new IllegalArgumentException("Invalid coin value. Expected one of: " + validCoinsReverse);
    }

    @Override
    public Collection<Coin> cancelAndGetChange() {
        List<Coin> change = getChange(deposit);
        basket = new HashMap<>();
        deposit = 0;
        return change;
    }

    private List<Coin> getChange(int changeValue) {
        if (changeValue < 0) {
            throw new IllegalArgumentException("Change cannot be negative. Change=" + changeValue);
        }
        List<Coin> change = new ArrayList<>();
        for (Integer value : validCoinsReverse) {
            while (changeValue >= value && changeValue != 0) {
                if (coinRemainder.get(new Coin(value)) > 0) {
                    change.add(new Coin(value));
                    coinRemainder.put(new Coin(value), coinRemainder.get(new Coin(value)) - 1);  //decrease coins quantity
                    changeValue -= value;
                } else {
                    break;
                }
            }
        }
        return change;
    }

    @Override
    public Collection<Coin> getAllowedCoins() {
        return Arrays.asList(new Coin(1), new Coin(5), new Coin(10), new Coin(25), new Coin(50));
    }

    @Override
    public Collection<Product> getAvailableProducts() {
        return null;
    }

    @Override
    public void addProductToBasket(Product product) {

    }

    public int getDeposit() {
        return deposit;
    }

    @Override
    public List<Sale> getSalesList() {
        return null;
    }

    @Override
    public List<Coin> checkout() {
        return null;
    }

    public Map<String, Integer> getBasket() {
        return basket;
    }

    public void addProduct(String product) {
        Integer productCount = basket.get(product);
        basket.put(product, productCount == null ? 1 : productCount + 1);
    }
}
