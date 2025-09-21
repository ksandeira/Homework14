package org.skypro.skyshop.product;

public abstract class Product {
    private final String name;
    private final int cost;

    public Product(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public abstract  boolean isSpecial();

    @Override
    public String toString() {
        return getName() + ": " + getCost() + " руб.";
    }
}
