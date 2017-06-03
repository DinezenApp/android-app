package com.dinezen.www.dinezen.menu;

import android.util.Log;

import com.dinezen.www.dinezen.backendUtilities.CompletedCallback;
import com.dinezen.www.dinezen.backendUtilities.Requestor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nick on 5/26/2017. A singleton class that facilitates storing and loading menus from the server.
 */

public class Menus {
    public static final Menu testInstance = new Menu(new HashMap<String, List<MenuItem>>());

    private static String TAG = "MENUS";
    private static Menus instance;
    private HashMap<MenuIdentifier, Menu> cache;
    private HashMap<MenuIdentifier, ArrayList<MenuCallback>> loading;

    private Menus() {
        cache = new HashMap<>();
        loading = new HashMap<>();
        testInstance.getAreaItems().put("1", new ArrayList<MenuItem>());
        testInstance.getAreaItems().get("1").add(new MenuItem("a", null));
        testInstance.getAreaItems().put("2", new ArrayList<MenuItem>());
        testInstance.getAreaItems().get("2").add(new MenuItem("b", null));
        testInstance.getAreaItems().get("2").add(new MenuItem("c", null));


    }

    /**
     * Asynchronously loads and saves the specified menu. Optionally supply a CompletedCallback.
     */
    public void loadMenu(final Menu.Location location, final Menu.Meal meal, final Menu.Date date) {
        loadMenu(location, meal, date, null);
    }

    /**
     * Asynchronously loads and saves the specified menu. Calls either complete or error on the
     * CompletedCallback when done.
     * If the specified menu is already loaded, the callback is immediately called.
     */
    public void loadMenu(final Menu.Location location, final Menu.Meal meal, final Menu.Date date, final MenuCallback callback) {
        final MenuIdentifier id = new MenuIdentifier(location, meal, date);
        Log.i(TAG, "Loading menu " + id);

        if(hasMenu(location, meal, date)) {
            if(callback != null)
                callback.menu(getMenu(id));
            return;
        }
        loading.put(id, new ArrayList<MenuCallback>());
        Requestor.getInstance().getMenu(Menu.Location.NORTH_CAMPUS_DINER, new Menu.Date(22, 2, 2017), Menu.Meal.LUNCH, new MenuCallback() {
            @Override
            public void menu(Menu menu) {
                if(callback != null)
                    callback.menu(menu);
                cache.put(id, menu);
                loadingSuccess(id, menu);
            }

            @Override
            public void error(String error) {
                Log.e(TAG+":", error);
                if(callback != null)
                    callback.error(error);
                loadingFailure(id, error);
            }
        });
    }

    /**
     * Called when a menu is done loading. Updates loading status and calls callbacks
     */
    private void loadingSuccess(MenuIdentifier id, Menu menu) {
        ArrayList<MenuCallback> callbacks = loading.get(id);
        for(MenuCallback c : callbacks) {
            c.menu(menu);
        }
        loading.remove(id);
    }

    /**
     * Called when a menu is failed loading. Updates loading status and calls callbacks
     */
    private void loadingFailure(MenuIdentifier id, String error) {
        ArrayList<MenuCallback> callbacks = loading.get(id);
        for(MenuCallback c : callbacks) {
            c.error(error);
        }
        loading.remove(id);
    }


    /**
     * Adds a callback to a currently loading menu.
     */
    public void waitForMenu(Menu.Location location, Menu.Meal meal, Menu.Date date, MenuCallback callback) {
        waitForMenu(new MenuIdentifier(location, meal, date), callback);
    }

    /**
     * Adds a callback to a currently loading menu.
     */
    public void waitForMenu(MenuIdentifier id, MenuCallback callback) {
        if(!isLoadingMenu(id)) {
            if(hasMenu(id)) callback.menu(getMenu(id));
            else callback.error("Menu is not being loaded");
        } else {
            loading.get(id).add(callback);
        }
    }

    /**
     * Retrieves a loaded menu. If the menu is not loaded, returns null.
     */
    public Menu getMenu(Menu.Location location, Menu.Meal meal, Menu.Date date) {
        return getMenu(new MenuIdentifier(location, meal, date));
    }

    /**
     * Retrieves a loaded menu. If the menu is not loaded, returns null.
     */
    public Menu getMenu(MenuIdentifier id) {
        return cache.get(id);
    }

    /**
     * Returns true if the specified menu has been loaded and can be retrieved with getMenu(...)
     */
    public boolean hasMenu(Menu.Location location, Menu.Meal meal, Menu.Date date) {
        return hasMenu(new MenuIdentifier(location, meal, date));
    }

    /**
     * Returns true if the specified menu has been loaded and can be retrieved with getMenu(...)
     */
    public boolean hasMenu(MenuIdentifier id) {
        return cache.containsKey(id);
    }

    /**
     * Returns true if the specified menu is currently being loaded.
     */
    public boolean isLoadingMenu(Menu.Location location, Menu.Meal meal, Menu.Date date) {
        return isLoadingMenu(new MenuIdentifier(location, meal, date));
    }

    /**
     * Returns true if the specified menu is currently being loaded.
     */
    public boolean isLoadingMenu(MenuIdentifier id) {
        return loading.containsKey(id);
    }

    /**
     * Returns an instance of the Menus singleton class.
     */
    public static Menus getInstance() {
        if(instance == null) {
            instance = new Menus();
        }
        return instance;
    }
}
