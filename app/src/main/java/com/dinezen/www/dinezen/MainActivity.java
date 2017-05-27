package com.dinezen.www.dinezen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.dinezen.www.dinezen.backendUtilities.CompletedCallback;
import com.dinezen.www.dinezen.backendUtilities.Requestor;
import com.dinezen.www.dinezen.menu.Menu;
import com.dinezen.www.dinezen.menu.MenuFragment;
import com.dinezen.www.dinezen.menu.Menus;


public class MainActivity extends AppCompatActivity implements MenuFragment.OnListFragmentInteractionListener {

    private static String TAG = "MAIN_ACTIVITY";
    private FrameLayout  contentView;
    private FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_meals:
                    //fragmentManager.beginTransaction().replace(R.id.content, new MealsFragment()).commit();
                    return true;
                case R.id.navigation_menu:
                    Menus.getInstance().waitForMenu(Menu.Location.NORTH_CAMPUS_DINER, Menu.Meal.LUNCH, new Menu.Date(22, 2, 2017), new CompletedCallback() {
                        @Override
                        public void complete() {
                            fragmentManager.beginTransaction().replace(R.id.content, new MenuFragment()).commit();
                        }

                        @Override
                        public void error(String error) {
                            Log.e(TAG, error);
                        }
                    });
                    return true;
                case R.id.navigation_search:
                    //fragmentManager.beginTransaction().replace(R.id.content, new SearchFragment()).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Requestor.getInstance(getApplicationContext());
        fragmentManager = getSupportFragmentManager();
        contentView = (FrameLayout) findViewById(R.id.content);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menus.getInstance().loadMenu(Menu.Location.NORTH_CAMPUS_DINER, Menu.Meal.LUNCH, new Menu.Date(22, 2, 2017));
    }

    @Override
    public void onListFragmentInteraction(com.dinezen.www.dinezen.menu.MenuItem item) {

    }
}
