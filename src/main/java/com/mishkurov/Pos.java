package com.mishkurov;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pos implements PosInt {

    private int deposit;
    private int[] validCoins = new int[]{1, 5, 10, 25, 50};
    private Map<String, Integer> basket;

    public Pos() {
        this.deposit = 0;
        basket = new HashMap<>();
    }

    public void insertCoin(Coin coin) {
        if (coin.getValue() <= 0) {
            throw new IllegalArgumentException("Coin value must be positive");
        }
        for (int validCoin : validCoins) {
            if (coin.getValue() == validCoin) {
                deposit += coin.getValue();
                return;
            }
        }
        throw new IllegalArgumentException("Invalid coin value. Expected one of: " + validCoins);
    }

    @Override
    public Collection<Coin> cancelAndGetChange() {
        return null;
    }

    @Override
    public Collection<Coin> getAllowedCoins() {
        return null;
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
