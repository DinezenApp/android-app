package com.dinezen.www.dinezen.menu;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Nick on 6/2/2017.
 */

public interface MenuListItem {

    public int getViewType();
    public void bind(final RecyclerView.ViewHolder holder, final MenuFragment.OnListFragmentInteractionListener listener);
}
