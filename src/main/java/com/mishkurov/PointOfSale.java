package com.mishkurov;

import com.mishkurov.coins.Coin;
import com.mishkurov.products.Product;
import com.mishkurov.sales.Sale;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Anton_Mishkurov
 */
public interface PointOfSale {
        void insertCoin(Coin coin);

        Collection<Coin> cancelAndGetChange();

        Collection<Coin> getAllowedCoins();

        Collection<Product> getAvailableProducts();

        Map<Product, Integer> getBasket();

        void addProductToBasket(Product product);

        int getDeposit();

        List<Sale> getSalesList();

        List<Coin> checkout();
}
