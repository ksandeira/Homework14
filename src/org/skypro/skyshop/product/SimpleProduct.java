package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    public SimpleProduct(String name, int cost) {
        super(name, cost);
        if (cost <= 0) {
            throw new IllegalArgumentException("Цена продукта должна быть строго больше 0. Передано: " + cost);
        }
    }
    @Override
    public boolean isSpecial() {
        return false;
    }
}
