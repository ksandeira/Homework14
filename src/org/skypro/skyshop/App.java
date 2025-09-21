package org.skypro.skyshop;

import org.skypro.skyshop.product.*;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;

public class App {
    public static void main(String[] args) {
        Product dogFood = new SimpleProduct("Корм для собак сухой", 4000);
        Product catFood = new SimpleProduct("Корм для стерилизованных кошек", 2500);

        Product aquarium = new DiscountedProduct("Аквариум 50л с подсветкой", 8000, 10);
        Product parrotCage = new DiscountedProduct("Клетка для попугаев большая", 6000, 15);

        Product hamsterWheel = new FixPriceProduct("Беговое колесо для хомяка");
        Product dogCollar = new FixPriceProduct("Кожаный ошейник для собак");

        Product catTree = new SimpleProduct("Игровой комплекс для кошек",8950);
        Product fishFood = new SimpleProduct("Корм для рыб",650);
        Product parrotFood = new SimpleProduct("Корм для попугаев",750);

        ProductBasket basket = new ProductBasket();

        basket.addProduct(dogCollar);
        basket.addProduct(dogFood);
        basket.addProduct(catFood);
        basket.addProduct(catTree);

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

        System.out.println("\nПопытка добавить пятый товар:");
        Product testProduct = new SimpleProduct("Тестовый товар", 1000);
        basket.addProduct(testProduct);
        System.out.println("Корзина полна: " + basket.isFull());
    }
}
