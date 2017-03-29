package com.dinezen.www.dinezen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nick on 3/29/17.
 */

public class Menu {

    public static int
            SOUTH_CAMPUS_DINER = 51,
            NORTH_CAMPUS_DINER = 4,
            TWO_FIFTY_ONE = 16;

    private List<MenuItem> items;
    private static Menu testInstance;

    public Menu(List<MenuItem> items) {
        this.items = items;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public static Menu getTestInstance() {
        if(testInstance == null) {
            List<MenuItem> items = new ArrayList<>();
            items.add(new MenuItem("carrot", new NutritionInfo(100)));
            items.add(new MenuItem("squash", new NutritionInfo(44)));
            items.add(new MenuItem("spam", new NutritionInfo(75)));
            items.add(new MenuItem("pineapple", new NutritionInfo(130)));
            items.add(new MenuItem("pizza", new NutritionInfo(42)));

            testInstance = new Menu(items);
        }
        return testInstance;
    }
}
