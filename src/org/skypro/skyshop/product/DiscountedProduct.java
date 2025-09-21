package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int discountPercent;

    public DiscountedProduct(String name, int basePrice, int discountPercent) {
        super(name, basePrice * (100 - discountPercent) / 100);
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
