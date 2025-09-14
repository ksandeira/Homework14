package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private static final int MAX_CAPACITY = 5;
    private final Product[] products;
    private int productCount;

    public ProductBasket() {
        this.products = new Product[MAX_CAPACITY];
        this.productCount = 0;
    }

    public void addProduct(Product product) {
        if (productCount < MAX_CAPACITY) {
            products[productCount] = product;
            productCount++;
        } else {
            System.out.println("Корзина заполнена!" + product.getName());
        }
    }

    public int getTotalCost() {
        int total = 0;
        for (int i = 0; i < productCount; i++) {
            total += products[i].getCost();
        }
        return total;
    }

    public void printBasket() {
        if (productCount == 0) {
            System.out.println("В корзине пусто");
            return;
        }
        for (int i = 0; i < productCount; i++) {
            System.out.println(products[i].getName() + ": " + products[i].getCost() + " руб.");
        }
        System.out.println("Итого: " + getTotalCost() + " руб.");
    }

    public boolean containsProductByName(String name) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < productCount; i++) {
            products[i] = null;
        }
        productCount = 0;
    }

}
