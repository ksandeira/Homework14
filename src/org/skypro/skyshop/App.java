package org.skypro.skyshop;

import org.skypro.skyshop.product.*;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.exception.BestResultNotFound;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        System.out.println("Демонстрация проверки данных:");
        try {
            Product invalidProduct1 = new SimpleProduct("", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта: " + e.getMessage());
        }

        try {
            Product invalidProduct2 = new SimpleProduct("Тестовый товар", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта: " + e.getMessage());
        }

        try {
            Product invalidProduct3 = new DiscountedProduct("Товар со скидкой", 1000, -10);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта со скидкой: " + e.getMessage());
        }

        try {
            Product invalidProduct4 = new DiscountedProduct("Товар со скидкой", 1000, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании продукта со скидкой: " + e.getMessage());
        }

        Product dogFood = new SimpleProduct("Корм для собак сухой", 4000);
        Product catFood = new SimpleProduct("Корм для стерилизованных кошек", 2500);

        Product aquarium = new DiscountedProduct("Аквариум 50л с подсветкой", 8000, 10);
        Product parrotCage = new DiscountedProduct("Клетка для попугаев большая", 6000, 15);

        Product hamsterWheel = new FixPriceProduct("Беговое колесо для хомяка");
        Product dogCollar = new FixPriceProduct("Кожаный ошейник для собак");

        Product catTree = new SimpleProduct("Игровой комплекс для кошек", 8950);
        Product fishFood = new SimpleProduct("Корм для рыб", 650);
        Product parrotFood = new SimpleProduct("Корм для попугаев", 750);

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

        System.out.println("Демонстрация удаления продуктов по имени:");

        System.out.println("1. Удаление существующего продукта 'Кожаный ошейник для собак':");
        List<Product> removedProducts = basket.removeProductsByName("Кожаный ошейник для собак");
        System.out.println("Удаленные продукты:");
        if (!removedProducts.isEmpty()) {
            for (Product product : removedProducts) {
                System.out.println(" - " + product);
            }
        }
        System.out.println("Содержимое корзины после удаления:");
        basket.printBasket();

        System.out.println("Удаление несуществующего продукта 'Несуществующий товар':");
        List<Product> removedNonExistent = basket.removeProductsByName("Несуществующий товар");
        if (removedNonExistent.isEmpty()) {
            System.out.println("Список удаленных продуктов пуст");
        }
        System.out.println("Содержимое корзины после попытки удаления несуществующего товара:");
        basket.printBasket();

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

        System.out.println("Попытка добавить пятый товар:");
        Product testProduct = new SimpleProduct("Тестовый товар", 1000);
        basket.addProduct(testProduct);
        System.out.println("Корзина полна: " + basket.isFull());

        System.out.println("Демонстрация поиска: ");

        String[] searchQueries = {"корм", "аквариум", "попугай", "уход"};
        for (String query : searchQueries) {
            System.out.println("Результаты поиска по запросу: '" + query + "'");
            List<Searchable> results = searchEngine.search(query);

            if (!results.isEmpty()) {
                for (Searchable result : results) {
                    System.out.println("- " + result.getStringRepresentation());
                }
            } else {
                System.out.println("Ничего не найдено");
            }
        }

        System.out.println("Проверка ограничения в 5 результатов (теперь нет ограничения):");
        List<Searchable> manyResults = searchEngine.search("о");
        System.out.println("Найдено результатов: " + manyResults.size());

        System.out.println("Детальная информация о найденных объектах:");
        List<Searchable> detailedResults = searchEngine.search("корм для кошек");
        for (Searchable result : detailedResults) {
            System.out.println("Тип: " + result.getContentType());
            System.out.println("Представление: " + result.getStringRepresentation());
            if (result instanceof Product) {
                System.out.println("Стоимость: " + ((Product) result).getCost() + " руб.");
            } else if (result instanceof Article) {
                String content = ((Article) result).getContent();

                System.out.println("Содержание: " + (content.length() > 50 ? content.substring(0,50) + "..." : content));
            }
        }

        System.out.println("Демонстрация нового метода поиска:");

        try {
            Searchable bestMatch = searchEngine.findBestMatch("корм");
            System.out.println("Наиболее подходящий результат для 'корм': " + bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка при поиске: " + e.getMessage());
        }

        try {
            Searchable bestMatch = searchEngine.findBestMatch("несуществующий запрос");
            System.out.println("Наиболее подходящий результат: " + bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка при поиске: " + e.getMessage());
        }

        String[] testQueries = {"собак", "кошек", "попугаев", "рыб"};
        for (String query : testQueries) {
            try {
                Searchable bestMatch = searchEngine.findBestMatch(query);
                System.out.println("Лучший результат для '" + query + "':" + bestMatch.getStringRepresentation());
            } catch (BestResultNotFound e) {
                System.out.println("Для запроса '" + query + "' ничего не найдено: " + e.getMessage());
            }
        }
    }
}