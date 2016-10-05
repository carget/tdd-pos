package com.mishkurov;

import com.mishkurov.coins.Coin;
import com.mishkurov.products.Product;
import com.mishkurov.sales.Sale;

import java.util.Collection;
import java.util.List;

/**
 * @author Anton_Mishkurov
 */
public interface PosInt {
        void insertCoin(Coin coin);

        Collection<Coin> cancelAndGetChange();

        Collection<Coin> getAllowedCoins();

        Collection<Product> getAvailableProducts();

        void addProductToBasket(Product product);

        int getDeposit();

        List<Sale> getSalesList();

        List<Coin> checkout();
}
