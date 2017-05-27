package com.dinezen.www.dinezen.menu;

/**
 * Stores information for a food item in a menu
 */

public class MenuItem {
    private String name;
    private NutritionInfo nutrition;

    public MenuItem(String name, NutritionInfo nutrition) {
        this.name = name;
        this.nutrition = nutrition;
    }

    public NutritionInfo getNutrition() {
        return nutrition;
    }

    public String getName() {
        return name;
    }
}
