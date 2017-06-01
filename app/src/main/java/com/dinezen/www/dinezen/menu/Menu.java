package com.dinezen.www.dinezen.menu;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A menu containing MenuItems for a specified date, location, and meal.
 */

public class Menu {

    public enum Location {
        SOUTH_CAMPUS_DINER("51"), NORTH_CAMPUS_DINER("04"), TWO_FIFTY_ONE("16");

        private final String id;
        Location(String id) { this.id = id; }
        public String getId() { return id; }
    }

    public enum Meal {
        BREAKFAST("Breakfast"), LUNCH("Lunch"), DINNER("Dinner");

        private final String name;
        Meal(String name) { this.name = name; }
        public String getName() { return name; }
    }

    public static class Date{
        private int day, month, year;
        public Date() {
            this.day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            this.month = Calendar.getInstance().get(Calendar.MONTH);
            this.year = Calendar.getInstance().get(Calendar.YEAR);

        }
        public Date(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }
        public int getDay() {
            return day;
        }
        public int getMonth() {
            return month;
        }
        public int getYear() {
            return year;
        }
        @Override
        public String toString() {
            return month+"/"+day+"/"+year;
        }
        public String toURLString() {
            return month+"%2F"+day+"%2F"+year;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Date date = (Date) o;

            if (day != date.day) return false;
            if (month != date.month) return false;
            return year == date.year;

        }

        @Override
        public int hashCode() {
            int result = day;
            result = 31 * result + month;
            result = 31 * result + year;
            return result;
        }
    }


    public static String TAG = "MENU";
    private List<MenuItem> items;
    private Location location;
    private Meal meal;
    private Date date;

    public Menu(Location location, Meal meal, Date date) {
        this.location = location;
        this.meal = meal;
        this.date = date;

    }

    public Menu(List<MenuItem> items) {
        this.items = items;
    }

    /**
     * Creates a menu from a JSON array given from the get_full_menu.json endpoint.
     * @throws JSONException
     */
    public Menu(JSONArray json) throws JSONException {
        this.items = new ArrayList<>();
        for(int i = 0; i < json.length(); i++) {
            JSONObject zone = json.getJSONObject(i);
            Log.d(TAG, "Loading " + zone.getString("area"));
            JSONArray subMenu = zone.getJSONArray("menu");
            for(int j = 0; j < subMenu.length(); j++) {
                JSONObject jsonItem = subMenu.getJSONObject(j);
                String name = jsonItem.getString("name");

                JSONObject jsonNutrition = jsonItem.optJSONObject("nutrition");
                NutritionInfo nutrition = new NutritionInfo();
                if(jsonNutrition != null) {
                    nutrition.setPortionNum(jsonNutrition.optInt("portionnum"));
                    nutrition.setPortionType(jsonNutrition.optString("portionunits"));
                    nutrition.setCalories(jsonNutrition.optInt("calories"));
                    nutrition.setFatcalories(jsonNutrition.optInt("fatcalories"));
                    nutrition.setTotalfat(jsonNutrition.optDouble("totalfat"));
                    nutrition.setCarb(jsonNutrition.optDouble("carb"));
                    nutrition.setSatfat(jsonNutrition.optDouble("satfat"));
                    nutrition.setFiber(jsonNutrition.optDouble("fiber"));
                    nutrition.setTransfat(jsonNutrition.optDouble("transfat"));
                    nutrition.setSugar(jsonNutrition.optDouble("sugar"));
                    nutrition.setCholesterol(jsonNutrition.optDouble("cholesterol"));
                    nutrition.setProtein(jsonNutrition.optDouble("protein"));
                    nutrition.setSodium(jsonNutrition.optDouble("sodium"));
                    JSONArray allergensJSON = jsonNutrition.optJSONArray("allergens");
                    if(allergensJSON != null) {
                        String[] allergens = new String[allergensJSON.length()];
                        for(int k = 0; k < allergensJSON.length(); k++) {
                            allergens[k] = allergensJSON.getString(k);
                        }
                        nutrition.setAllergens(allergens);
                    }
                }

                MenuItem item = new MenuItem(name, nutrition);
                items.add(item);
            }
        }
    }

    /**
     * Returns all of the MenuItems in the menu.
     */
    protected List<MenuItem> getItems() {
        return items;
    }
}
