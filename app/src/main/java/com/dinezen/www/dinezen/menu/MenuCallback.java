package com.dinezen.www.dinezen.menu;

/**
 * Used for retrieving menus from the Menus class.
 */

public interface MenuCallback {
    void menu(Menu menu);
    void error(String erro8r);
}
