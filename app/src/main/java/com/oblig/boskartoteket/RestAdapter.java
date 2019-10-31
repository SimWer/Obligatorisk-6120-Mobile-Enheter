package com.oblig.boskartoteket;

import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 *  En adapterklasse for å gjennomføre REST-kall til DB mer oversiktlig
 *  Hver metode gjør en operasjon - som SELECT, UPDATE, DELETE, INSERT INTO
 *  Mesteparten av koden er basert på undervisningsnotater, ingenting er copy paste men skrevet inn manuelt.
 *
 * */

public class RestAdapter {

    static final String ENDPOINT = "https://itfag.usn.no/~216734/api.php";



    private Context context;
    private RequestQueue queue;

    public RestAdapter(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public void createUser(User newUser) {
        String userUrl = ENDPOINT + "/User_new";
        JSONObject jsonUser = newUser.toJSONObject();
        addJSONRequest(Request.Method.POST, userUrl, jsonUser);
    }

    public void updateUser(User updateUser, int id) {
        String userUrl = ENDPOINT + "/User_new/" + id;
        JSONObject jsonUser = updateUser.toJSONObject();
        addJSONRequest(Request.Method.PUT, userUrl, jsonUser);
    }


    private void addJSONRequest(int requestMethod, String url, JSONObject data) {
        if(isOnline()) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestMethod, url, data, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(jsonObjectRequest);
            }
    }



    public boolean isOnline() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
