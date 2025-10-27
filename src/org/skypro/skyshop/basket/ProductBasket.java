package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> productsMap;

    public ProductBasket() {
        this.productsMap = new HashMap<>();
    }

    public void addProduct(Product product) {
        String productName = product.getName();

        productsMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(product);
    }

    public int getTotalCost() {
        int total = 0;
        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                total += product.getCost();
            }
        }
        return total;
    }

    public void printBasket() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто.");
            return;
        }

        System.out.println("Содержимое корзины:");
        int specialCount = 0;
        int index = 1;

        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                System.out.println((index++) + ". " + product.toString());
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }
        System.out.println("Итого: " + getTotalCost() + " руб.");
        System.out.println("Специальных товаров: " + specialCount);
        System.out.println("Общее количество товаров: " + getProductCount());
    }

    public boolean containsProductByName(String name) {
        return productsMap.containsKey(name);
    }

    public void clearBasket() {
        productsMap.clear();
    }

    public int getProductCount() {
        int totalCount = 0;
        for (List<Product> productList : productsMap.values()) {
            totalCount += productList.size();
        }
        return totalCount;
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return productsMap.isEmpty();
    }

    public List<Product> removeProductsByName(String name) {
        List<Product> removedProducts = productsMap.remove(name);
        return removedProducts != null ? removedProducts : new ArrayList<>();
    }
}
