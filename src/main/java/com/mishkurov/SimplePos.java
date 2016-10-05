package com.mishkurov;

import com.mishkurov.coins.Coin;
import com.mishkurov.coins.CoinManager;
import com.mishkurov.products.Product;
import com.mishkurov.products.ProductManager;
import com.mishkurov.sales.Sale;

import java.time.LocalDate;
import java.util.*;

public class SimplePos implements PointOfSale {

    private static final int INITIAL_COIN_QUANTITY = 100;

    private CoinManager coinManager;
    private ProductManager productManager;
    private int deposit;
    private Map<Coin, Integer> coinRemainder;
    private List<Sale> saleList;
    private Map<Product, Integer> basket;

    public SimplePos() {
        coinManager = new CoinManager();
        productManager = new ProductManager();
        saleList = new ArrayList<>();
        this.deposit = 0;
        basket = new HashMap<>();
        coinRemainder = new HashMap<>();
        for (Coin c : getAllowedCoins()) {
            coinRemainder.put(c, INITIAL_COIN_QUANTITY);
        }
    }

    @Override
    public void insertCoin(Coin coin) {
        deposit += coin.getValue();
        coinManager.putCoin(coin);
    }

    @Override
    public Collection<Coin> cancelAndGetChange() {
        List<Coin> change = coinManager.getChange(deposit);
        basket = new HashMap<>();
        deposit = 0;
        return change;
    }

    @Override
    public Collection<Coin> getAllowedCoins() {
        return coinManager.getAllowedCoins();
    }

    @Override
    public Collection<Product> getAvailableProducts() {
        return productManager.getAvailableProducts();
    }

    public int getDeposit() {
        return deposit;
    }

    @Override
    public List<Sale> getSalesList() {
        return saleList;
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
        List<Coin> change = coinManager.getChange(changeValue);
        productManager.decreaseProductsAmountInStock(basket);
        deposit = 0;
        basket = new HashMap<>();
        saleList.add(sale);
        return change;
    }

    @Override
    public Map<Product, Integer> getBasket() {
        return basket;
    }

    @Override
    public void addProductToBasket(Product product) {
        Integer productCount = basket.get(product);
        basket.put(product, productCount == null ? 1 : productCount + 1);
    }

}
