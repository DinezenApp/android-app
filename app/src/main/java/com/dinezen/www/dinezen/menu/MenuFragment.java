package com.dinezen.www.dinezen.menu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dinezen.www.dinezen.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MenuFragment extends Fragment {

    private static String TAG = "MENU_FRAGMENT";
    private Menu menu;
    //private static final String ARG_COLUMN_COUNT = "column-count";
    private OnListFragmentInteractionListener listener;
    private MenuRecyclerViewAdapter adapter;
    private ViewGroup menuContainer;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MenuFragment() {
    }

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_COLUMN_COUNT, columnCount);
        //fragment.setArguments(args);
        return fragment;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        if(adapter == null) {
            adapter = new MenuRecyclerViewAdapter(menu, listener);
        } else {
            adapter.setMenu(menu);
        }
        Log.i(TAG, "Adapter menu has been set to: " + menu);
        showMenu();
    }

    private void showMenu() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.menu_list, menuContainer, false);
        Context context = view.getContext();
        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MenuRecyclerViewAdapter(menu, listener);
        recyclerView.setAdapter(adapter);
        menuContainer.removeAllViews();
        menuContainer.addView(view);
    }

     public void setError(String error) {
         menuContainer.removeAllViews();
         TextView errorView = new TextView(this.getContext());
         errorView.setText(error);
         errorView.setGravity(Gravity.CENTER);
         errorView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
         menuContainer.addView(errorView);
         Toast.makeText(getContext(), error, Toast.LENGTH_LONG);
     }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            //mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        menuContainer = (ViewGroup) view;
        if(menu != null) {
            showMenu();
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            listener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(MenuItem item);
    }
}
