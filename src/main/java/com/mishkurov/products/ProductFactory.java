package com.mishkurov.products;


import com.mishkurov.exceptions.ProductManagerException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductFactory {

    private static Map<Integer, Product> allowedProduct;

    private ProductFactory() {}

    static {
        allowedProduct = new HashMap<>();
        allowedProduct.put(1, new Product(1, "Tea", 25));
        allowedProduct.put(2, new Product(2, "Coffee", 35));
        allowedProduct.put(3, new Product(3, "Juice", 45));
    }

    public static Product getProductById(int id) {
        Product result = allowedProduct.get(id);
        if (result == null) {
            throw new ProductManagerException("Product with id=" + id + " is not allowed!");
        }
        return result;
    }

    public static Collection<Product> getAvailableProducts() {
        return allowedProduct.values();
    }
}
