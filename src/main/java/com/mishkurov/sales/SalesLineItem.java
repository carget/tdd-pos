package com.mishkurov.sales;

import com.mishkurov.products.Product;

public class SalesLineItem {
    private int quantity;
    private Product product;

    public SalesLineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int subtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "Product: " + product + " qty: " + quantity + " subtotal=" + subtotal();
    }
}
