package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;

public class App {
    public static void main(String[] args) {
        Product dogFood = new Product("Корм для собак сухой", 4000);
        Product catFood = new Product("Корм для стерилизованных кошек", 2500);
        Product aquarium = new Product("Аквариум 50л с подсветкой", 7900);
        Product parrotCage = new Product("Клетка для попугаев большая", 5500);
        Product hamsterWheel = new Product("Беговое колесо для хомяка", 1250);
        Product dogCollar = new Product("Кожаный ошейник для собак", 1400);
        Product catTree = new Product("Игровой комплекс для кошек", 8950);
        Product fishFood = new Product("Корм для рыб", 650);
        Product parrotFood = new Product("Корм для попугаев", 750);

        ProductBasket basket = new ProductBasket();

        basket.addProduct(dogCollar);
        basket.addProduct(dogFood);
        basket.addProduct(catFood);
        basket.addProduct(catTree);

        System.out.println("Содержимое корзины: ");
        basket.printBasket();

        System.out.println("Общая стоимость корзины: " + basket.getTotalCost() + " руб.");

        System.out.println("Поиск товара в корзине: ");
        System.out.println("Корм для попугаев: " +
                (basket.containsProductByName("Корм для попугаев") ? "найден" : "не найден"));

        System.out.println("Кожаный ошейник для собак: " +
                (basket.containsProductByName("Кожаный ошейник для собак") ? "найден" : "не найден"));

        System.out.println("Очистка корзины");
        basket.clearBasket();

        System.out.println("Содержимое корзины после очистки: ");
        basket.printBasket();

        System.out.println("Стоимость пустой корзины: " + basket.getTotalCost() + " руб.");

        System.out.println("Поиск товара в пустой корзине: ");
        System.out.println("Беговое колесо для хомяка: " +
                (basket.containsProductByName("Беговое колесо для хомяка") ? "найден" : "не найден"));

        System.out.println("Дополнительная корзина: ");
        ProductBasket secondBasket = new ProductBasket();
        secondBasket.addProduct(fishFood);
        secondBasket.addProduct(aquarium);
        secondBasket.addProduct(parrotFood);
        secondBasket.addProduct(parrotCage);

        System.out.println("Содержимое второй корзины: ");
        secondBasket.printBasket();

        System.out.println("Демонстрация поиска: ");
        String[] searchItems = {"Корм для рыб", "Беговое колесо для хомяка", "Клетка для попугаев большая"};
        for (String item : searchItems) {
            boolean found = secondBasket.containsProductByName(item);
            System.out.println("'" + item + "': " + (found ? "В корзине" : "Нет в корзине"));
        }
    }
}
