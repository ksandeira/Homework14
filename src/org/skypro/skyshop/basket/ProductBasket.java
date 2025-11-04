package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;
import java.util.stream.Collectors;

public class ProductBasket {
    private final List<Product> products;

    public ProductBasket() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalCost() {
        return products.stream().mapToInt(Product::getCost).sum();
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто.");
            return;
        }

        System.out.println("Содержимое корзины:");
        int specialCount = getSpecialCount();
        final int[] index = {1};

        products.forEach(product -> {System.out.println((index[0]++) + ". " + product.toString());});

        System.out.println("Итого: " + getTotalCost() + " руб.");
        System.out.println("Специальных товаров: " + specialCount);
        System.out.println("Общее количество товаров: " + getProductCount());
    }

    private int getSpecialCount() {
        return (int) products.stream().filter(Product::isSpecial).count();
    }

    public boolean containsProductByName(String name) {
        return products.stream().anyMatch(product -> product.getName().equals(name));
    }

    public void clearBasket() {
        products.clear();
    }

    public int getProductCount() {
        return products.size();
    }

    public boolean isFull() {
        return false;
    }

    public List<Product> removeProductsByName(String name){
        List<Product> removedProducts = products.stream().filter(product -> product.getName().equals(name)).collect(Collectors.toList());
        products.removeIf(product -> product.getName().equals(name));

        return removedProducts;
    }
}
