package com.oblig.boskartoteket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;


public class Register_User extends AppCompatActivity {

    private EditText fname, lname, email, password, address;
    private AutoCompleteTextView addressRegister;
    private User user;
    private RestAdapter restAdapter;
    private RequestQueue requestQueue;
    private final String url = "https://itfag.usn.no/~216734/api.php/Address?transform=1";


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

        dropDownSearch(this);


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

    /***
     *
     * Metode for å søke opp adresser når du registrerer bruker, den tar i mot søkeparameter og i "real-time" oppdaterer fra en liste med Addresse-objekter
     *
     */


    private void dropDownSearch(final Context context) {


        StringRequest request =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    ArrayList<Address> addressList = Address.addToAddressList(response);

                    addressRegister.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, addressList));


                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }

}
