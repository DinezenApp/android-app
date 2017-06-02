package com.dinezen.www.dinezen.menu;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dinezen.www.dinezen.R;
import com.dinezen.www.dinezen.menu.MenuFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MenuItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    protected static final int VIEW_AREA = 0, VIEW_ITEM = 1;
    private static final String TAG = "MenuRecyclerViewAdapter";
    private final List<MenuListItem> items;
    private final OnListFragmentInteractionListener listener;

    public MenuRecyclerViewAdapter(List<MenuListItem> items, OnListFragmentInteractionListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch(viewType) {
            case VIEW_AREA:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.area_header_item, parent, false);
                return new AreaHeaderViewHolder(view);
            case VIEW_ITEM:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.menu_item, parent, false);
                return new MenuItemViewHolder(view);
            default: return null;
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        items.get(position).bind(holder, listener);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MenuItemViewHolder extends ViewHolder {
        public final View view;
        public final TextView nutritionView;
        public final TextView nameView;

        public MenuItemViewHolder(View view) {
            super(view);
            this.view = view;
            this.nutritionView = (TextView) view.findViewById(R.id.nutrition);
            this.nameView = (TextView) view.findViewById(R.id.name);
        }

        @Override
        public String toString() {
            return super.toString() + " FOOD ITEM: '" + nameView.getText() + "'";
        }
    }
    public class AreaHeaderViewHolder extends ViewHolder {
        public final View view;
        public final TextView nameView;

        public AreaHeaderViewHolder(View view) {
            super(view);
            this.view = view;
            this.nameView = (TextView) view.findViewById(R.id.name);
        }

        @Override
        public String toString() {
            return super.toString() + " AREA: '" + nameView.getText() + "'";
        }
    }

}
