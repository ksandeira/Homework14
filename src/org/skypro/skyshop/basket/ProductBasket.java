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
            System.out.println("Корзина заполнена! Нельзя добавить: " + product.getName());
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

        System.out.println("Содержимое корзины:");
        int specialCount = 0;
        for (int i = 0; i < productCount; i++) {
            System.out.println((i + 1) + ". " + products[i].toString());
            if (products[i].isSpecial()) {
                specialCount++;
            }
        }

        System.out.println("Итого: " + getTotalCost() + " руб.");
        System.out.println("Специальных товаров: " + specialCount);
        System.out.println("Общее количество товаров: " + productCount);
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
        productCount = 0;
    }

    public int getProductCount() {
        return productCount;
    }

    public boolean isFull() {
        return productCount >= MAX_CAPACITY;
    }

    public boolean isEmpty() {
        return productCount == 0;
    }
}
