package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    public SimpleProduct(String name, int cost) {
        super(name, cost);
    }
    @Override
    public boolean isSpecial() {
        return false;
    }
}
