package com.dinezen.www.dinezen;

/**
 * Created by nick on 3/29/17.
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
