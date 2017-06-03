package com.dinezen.www.dinezen.backendUtilities;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dinezen.www.dinezen.R;
import com.dinezen.www.dinezen.menu.Menu;
import com.dinezen.www.dinezen.menu.MenuCallback;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * Created by Nick on 5/25/2017. A network call helper singleton class.
 */

public class Requestor {
    private static final String TAG = "REQUESTOR";
    /**
     * Request timeout in ms
     */
    private int timeout = 5000;
    private static Requestor instance;

    private static RequestQueue queue;
    private Context context;

    private Requestor(Context context) {
        queue = Volley.newRequestQueue(context);
        this.context = context;
    }

    /**
     * Asynchronously requests the specified menu's JSON, constructs a new menu from it, and returns
     * the menu in a callback.
     */
    public void getMenu(Menu.Location location, Menu.Date date, Menu.Meal meal, final MenuCallback callback) {
        final String reqString = context.getResources().getString(
                R.string.api_url) + "/get_full_menu.json?location_id="+location.getId()+
                "&meal_name="+meal.getName()+"&date="+date.toURLString();
        final JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, reqString, null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null) {
                            try {
                                Menu menu = new Menu(response);
                                Log.d(TAG, "Received menu from " + reqString);
                                callback.menu(menu);
                            } catch (JSONException e){
                                callback.error(e.toString());
                            }
                        }
                    }
                },

                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.error(error.toString());
                        Log.e(TAG, "Error status code: " + error.networkResponse +
                                " From " + reqString);
                    }
                });

        req.setRetryPolicy(new DefaultRetryPolicy(
                timeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(req);
    }

    /**
     * Returns an instance of the Requestor singleton class. If an instance has not been created,
     * a new Requestor instance is created using the given context. For subsequent calls,
     * getInstance() can be used instead.
     */
    public static Requestor getInstance(Context context) {
        if(instance == null) {
            instance = new Requestor(context);
        }
        return instance;
    }

    /**
     * Returns an instance of the Requestor singleton class. getInstance(Context) must be called
     * prior to this method.
     */
    public static Requestor getInstance() {
        if(instance == null) {
            Log.e(TAG + ":", "You must call getInstance(Context) before calling getInstance().");
        }
        return instance;
    }

}
