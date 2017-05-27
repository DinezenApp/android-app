package com.dinezen.www.dinezen.menu;

/**
 * Stores nutrition information for MenuItem
 */

public class NutritionInfo {
    private enum PortionType{EACH, OUNCE, GRAM}
    private int calories;
    private int portionNum;
    private PortionType portionType;

    public NutritionInfo(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }
}
