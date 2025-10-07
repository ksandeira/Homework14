package org.skypro.skyshop.search;

import org.skypro.skyshop.exception.BestResultNotFound;

public class SearchEngine {
    private final Searchable[] searchables;
    private int currentIndex;

    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
        this.currentIndex = 0;
    }

    public void add(Searchable searchable) {
        if (currentIndex < searchables.length) {
            searchables[currentIndex] = searchable;
            currentIndex++;
        } else {
            System.out.println("Нельзя добавить: " + searchable.getName());
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int resultIndex = 0;
        for (int i = 0; i < currentIndex && resultIndex < 5; i++) {
            Searchable item = searchables[i];
            if (item != null && item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results[resultIndex] = item;
                resultIndex++;
            }
        }
        return results;
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new BestResultNotFound("Поисковый запрос не может быть пустым");
        }
        Searchable bestMatch = null;
        int maxCount = -1;
        String searchLower = search.toLowerCase();

        for (int i = 0; i < currentIndex; i++) {
            Searchable item = searchables[i];
            if (item != null) {
                String searchTerm = item.getSearchTerm().toLowerCase();
                int count = countOccurrences(searchTerm, searchLower);
                if (count > maxCount) {
                    maxCount = count;
                    bestMatch = item;
                }
            }
        }
        if (bestMatch == null) {
            throw new BestResultNotFound("Не найдено подходящих результатов для запроса: '" + search + "'");
        }
        return bestMatch;
    }

    private int countOccurrences(String text, String substring) {
        if (text == null || substring == null || substring.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        int substringLength = substring.length();

        while ((index = text.indexOf(substring, index)) != -1) {
            count++;
            index += substringLength;
        }
        return count;
    }
}