package com.mishkurov;

import java.time.LocalDate;
import java.util.*;

public class Pos implements PosInt {

    private static final int INITIAL_COIN_QUANTITY = 100;

    private static Map<Integer, Product> allowedProduct;

    static {
        allowedProduct = new HashMap<>();
        allowedProduct.put(1, new Product(1, "Tea", 25));
        allowedProduct.put(2, new Product(2, "Coffee", 35));
        allowedProduct.put(3, new Product(3, "Juice", 45));
    }

    private int deposit;
    private Integer[] validCoins = new Integer[]{1, 5, 10, 25, 50};
    private Integer[] validCoinsReverse = new Integer[]{50, 25, 10, 5, 1};
    private Map<Coin, Integer> coinRemainder;

    private Map<Product, Integer> basket;

    public Pos() {
        this.deposit = 0;
        basket = new HashMap<>();
        coinRemainder = new HashMap<>();
        for (Coin c : getAllowedCoins()) {
            coinRemainder.put(c, INITIAL_COIN_QUANTITY);
        }
    }

    @Override
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
        return allowedProduct.values();
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
        Sale sale = new Sale(LocalDate.now());
        for (Product p : basket.keySet()) {
            sale.makeLineItem(p, basket.get(p));
        }
        if (deposit < sale.total()) {
            System.out.println("Deposit is to low :-( Insert more coins.");
            return null;
        }
        int changeValue = deposit - sale.total();
        List<Coin> change = getChange(changeValue);
        deposit = 0;
        basket = new HashMap<>();
        return change;
    }

    public Map<Product, Integer> getBasket() {
        return basket;
    }

    @Override
    public void addProductToBasket(Product product) {
        Integer productCount = basket.get(product);
        basket.put(product, productCount == null ? 1 : productCount + 1);
    }

    public Product getProductById(int i) {
        return allowedProduct.get(i);
    }
}
