package org.skypro.skyshop.product;

public class FixPriceProduct extends Product{
    protected static final int FIXED_PRICE = 1000;

    public FixPriceProduct(String name) {
        super(name, FIXED_PRICE);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + FIXED_PRICE;
    }
}
