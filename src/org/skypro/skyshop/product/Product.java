package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;
    private final int cost;

    public Product(String name, int cost) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым или состоять только из пробелов");
        }
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

    @Override
    public String getSearchTerm() {
        return name;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }
}
