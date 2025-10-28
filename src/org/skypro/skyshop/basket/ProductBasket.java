package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;

public class ProductBasket {
    private final List<Product> products;

    public ProductBasket() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalCost() {
        int total = 0;
        for (Product product : products) {
            total += product.getCost();
        }
        return total;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто.");
            return;
        }

        System.out.println("Содержимое корзины:");
        int specialCount = 0;
        int index = 1;

        for (Product product : products) {
                System.out.println((index++) + ". " + product.toString());
                if (product.isSpecial()) {
                    specialCount++;
                }
        }
        System.out.println("Итого: " + getTotalCost() + " руб.");
        System.out.println("Специальных товаров: " + specialCount);
        System.out.println("Общее количество товаров: " + getProductCount());
    }

    public boolean containsProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
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

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public List<Product> removeProductsByName(String name){
        List<Product> removedProducts = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                removedProducts.add(product);
                iterator.remove();
            }
        }
        return removedProducts;
    }
}
