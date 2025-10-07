package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int discountPercent;

    public DiscountedProduct(String name, int basePrice, int discountPercent) {
        super(name, basePrice * (100 - discountPercent) / 100);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть строго больше 0. Передано: " + basePrice);
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть в диапазоне от 0 до 100. Передано: " + discountPercent);
        }
        this.discountPercent = discountPercent;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getCost() + " руб. (" + discountPercent + "%)";
    }
}
