package com.dinezen.www.dinezen.menu;

import android.support.annotation.NonNull;

/**
 * Created by Nick on 5/26/2017.
 * This class is used as a key to identify menus. It is used in to lookup menus in the cache (in the Menus class)
 */


public class MenuIdentifier implements Comparable<MenuIdentifier> {
    private Menu.Location location;
    private Menu.Date date;
    private Menu.Meal meal;

    public MenuIdentifier(Menu.Location location, Menu.Meal meal, Menu.Date date) {
        this.date = date;
        this.location = location;
        this.meal = meal;
    }


    @Override
    public int compareTo(@NonNull MenuIdentifier o) {
        if(location == o.location && date.equals(o.date) && meal == o.meal) return 0;
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuIdentifier that = (MenuIdentifier) o;

        if (location != that.location) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return meal == that.meal;

    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (meal != null ? meal.hashCode() : 0);
        return result;
    }
}
