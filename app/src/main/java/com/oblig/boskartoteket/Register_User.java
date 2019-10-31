package com.oblig.boskartoteket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Register_User extends AppCompatActivity {

    private EditText fname, lname, email, password, address;
    private AutoCompleteTextView addressRegister;
    private User user;
    private RestAdapter restAdapter;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__user);

        restAdapter = new RestAdapter(this);
        fname = findViewById(R.id.first_name_register);
        lname = findViewById(R.id.last_name_register);
        email = findViewById(R.id.email_register);
        password = findViewById(R.id.password_register);
        address = findViewById(R.id.address_register);
        addressRegister = findViewById(R.id.address_register);
        requestQueue = Volley.newRequestQueue(this);

        jsonParse(this);


    }

    /**
     *
     * Metode for å opprette et User-objekt med data fra det brukeren legger inn via EditText-fields.
     *
     * */
    private void registerFromView() {

        user = new User(fname.getText().toString(), lname.getText().toString(), email.getText().toString(), password.getText().toString(), address.getText().toString());

    }

    /**
     *
     * Metode for å lagre en bruker til databasen.
     *
     * */
    public void onAcceptClicked(View view) {

        registerFromView();
        restAdapter.createUser(user);
        finish();
    }


    public void dismissRegister(View view) {
        finish();
    }

    private void jsonParse(final Context context) {

        String url = "https://itfag.usn.no/~216734/api.php/Address";
        
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("Address");
                    JSONArray jsonArray = jsonObject.getJSONArray("records");
                    List<Address> list = new ArrayList<>();

                    String address = "";

                    String tempName, tempNumber, tempLetter, tempAddress = null;

                    String[] tempArray = new String[0];

                    for (int i = 0; i < jsonArray.length(); i++) {

                        address = jsonArray.getString(i);

                        list = Address.addToAddressList(address);

                        /*tempArray = address.split(",");

                        for (int j = 0; j < tempArray.length; j++) {
                            tempName    = tempArray[1];
                            tempNumber  = tempArray[2];
                            tempLetter  = tempArray[3];
                            tempAddress = tempName + " " + tempNumber + " " + tempLetter;
                        }
                        list.add(tempAddress);*/
                    }

                    addressRegister.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, list));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
    }
    
    /*private void jsonParse2(final Context context) {
        // Request a string response from the provided URL.
        String url = "https://itfag.usn.no/~216734/api.php/Address";
        
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonObject = response.getJSONObject("Address");
                        JSONArray jsonArray = jsonObject.getJSONArray("records");
                        List<Address> list = new ArrayList<>();

                        String address = "";

                        String tempName, tempNumber, tempLetter, tempAddress = null;

                        String[] tempArray = new String[0];

                        for (int i = 0; i < jsonArray.length(); i++) {

                            address = jsonArray.getString(i);

                            list = Address.addToAddressList(address);

                        *//*tempArray = address.split(",");

                        for (int j = 0; j < tempArray.length; j++) {
                            tempName    = tempArray[1];
                            tempNumber  = tempArray[2];
                            tempLetter  = tempArray[3];
                            tempAddress = tempName + " " + tempNumber + " " + tempLetter;
                        }
                        list.add(tempAddress);*//*
                        }

                        addressRegister.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, list));
                        
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                
            }
        });
    }*/
}
