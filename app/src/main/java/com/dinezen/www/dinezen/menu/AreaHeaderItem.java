package com.dinezen.www.dinezen.menu;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Nick on 6/2/2017.
 */

public class AreaHeaderItem implements MenuListItem {
    private String name;

    public AreaHeaderItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getViewType() {
        return MenuRecyclerViewAdapter.VIEW_AREA;
    }

    @Override
    public void bind(RecyclerView.ViewHolder viewHolder, MenuFragment.OnListFragmentInteractionListener listener) {
        final MenuRecyclerViewAdapter.AreaHeaderViewHolder holder = (MenuRecyclerViewAdapter.AreaHeaderViewHolder) viewHolder;
        holder.nameView.setText(name);
    }
}
