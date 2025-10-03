package org.skypro.skyshop;

import org.skypro.skyshop.product.*;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;

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

        Article dogCareArticle = new Article("Содержание собак", "Правильный уход за собаками включает регулярное кормление, ежедневные прогулки и своевременные ветеринарные осмотры.");

        System.out.println(dogCareArticle);

        Article catCareArticle = new Article("Содержание кошек", "Кошкам необходим правильно подобранный корм, игровые комплексы, а так же нуждаются в регулярном уходе.");

        System.out.println(catCareArticle);

        Article fishArticle = new Article("Содержание рыб", "Выбор аквариума, уход за рыбками и поддержание чистоты воды ");

        System.out.println(fishArticle);

        Article parrotArticle = new Article("Содержание попугаев", "Правильное питание попугаев, выбор клетки, а так же регулярный уход за ними");

        System.out.println(parrotArticle);

        SearchEngine searchEngine = new SearchEngine(20);

        searchEngine.add(catFood);
        searchEngine.add(aquarium);
        searchEngine.add(parrotCage);
        searchEngine.add(hamsterWheel);
        searchEngine.add(dogCollar);
        searchEngine.add(catTree);
        searchEngine.add(fishFood);
        searchEngine.add(parrotFood);

        searchEngine.add(dogCareArticle);
        searchEngine.add(catCareArticle);
        searchEngine.add(fishArticle);
        searchEngine.add(parrotArticle);

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

        System.out.println("Демонстрация поиска: ");

        String[] searchQueries = {"корм", "аквариум", "попугай", "уход"};
        for (String query : searchQueries) {
            System.out.println("Результаты поиска по запросу: '" + query + "'" );
            Searchable[] results = searchEngine.search(query);

            boolean foundAny = false;
            for (Searchable result : results) {
                if (result != null) {
                    System.out.println("-" + result.getStringRepresentation());
                    foundAny = true;
                }
            }
            if (!foundAny) {
                System.out.println("Ничего не найдено");
            }
        }

        System.out.println("Проверка ограничения в 5 результатов:");
        Searchable[] manyResults = searchEngine.search("о");
        System.out.println("Найдено результов(первые 5) : " + Arrays.toString(manyResults));

        System.out.println("Детальная информация о найденных объектах:");
        Searchable[] detailedResults = searchEngine.search("корм для кошек");
        for (Searchable result : detailedResults) {
            if (result != null) {
                System.out.println("Тип: " + result.getContentType());
                System.out.println("Представление: " + result.getStringRepresentation());
                if (result instanceof Product) {
                    System.out.println("Стоимость: " + ((Product) result).getCost() + " руб.");
                } else if (result instanceof Article) {
                    System.out.println("Содержание: " + ((Article) result).getContent().substring(0, 50) + "...");
                }
            }
        }
    }
}
