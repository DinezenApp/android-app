package com.dinezen.www.dinezen.menu;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dinezen.www.dinezen.R;
import com.dinezen.www.dinezen.menu.MenuFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MenuItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyMenuRecyclerViewAdapter extends RecyclerView.Adapter<MyMenuRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MenuRecylerViewAdapter";
    private final List<MenuItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyMenuRecyclerViewAdapter(List<MenuItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNutritionView.setText(""+mValues.get(position).getNutrition().getCalories());
        holder.mNameView.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    Toast.makeText(holder.mView.getContext(), holder.mItem.getNutrition().toString(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, holder.mItem.getNutrition().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNutritionView;
        public final TextView mNameView;
        public MenuItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNutritionView = (TextView) view.findViewById(R.id.nutrition);
            mNameView = (TextView) view.findViewById(R.id.name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
