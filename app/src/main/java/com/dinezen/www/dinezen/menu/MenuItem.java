package com.dinezen.www.dinezen.menu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

/**
 * Stores information for a food item in a menu
 */

public class MenuItem implements MenuListItem{
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

    @Override
    public int getViewType() {
        return MenuRecyclerViewAdapter.VIEW_ITEM;
    }

    @Override
    public void bind(RecyclerView.ViewHolder viewHolder, final MenuFragment.OnListFragmentInteractionListener listener) {
        final MenuRecyclerViewAdapter.MenuItemViewHolder holder = (MenuRecyclerViewAdapter.MenuItemViewHolder) viewHolder;
        holder.nutritionView.setText(""+nutrition.getCalories());
        holder.nameView.setText(name);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onListFragmentInteraction(MenuItem.this);
                    //TODO Actual menu item clicks
                    Toast.makeText(holder.view.getContext(), nutrition.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
