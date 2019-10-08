package com.oblig.boskartoteket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import static com.oblig.boskartoteket.RestAdapter.ENDPOINT;

public class MainActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener, Serializable {
    private EditText user_input, password_input, register_email, register_password;
    static final String MIN_ID = "boskartoteket-app-v1";
    private Button login_button, register_button;
    private User loggedInUser;

    private ArrayList<User> userArray;
    public final static String user_list = ENDPOINT + "/User_new?transform=1";

    RestAdapter restAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        restAdapter = new RestAdapter(this);
        user_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);

        getUserList();
    }


    /**
     *
     * Logger inn bruker, og sjekker om brukeren eksisterer med metoden isUser()
     *
     * */

    public void log_in_user(View view) {

        String email = user_input.getText().toString();
        String password = password_input.getText().toString();

        if(User.isUser(email, password, userArray)) {

            for(User uInArr: userArray) {
                if(uInArr.getEmail().equals(email) && uInArr.getPassword().equals(password)) {
                    loggedInUser = new User(uInArr.getId(), uInArr.getfNavn(), uInArr.geteNavn(), uInArr.getEmail(), uInArr.getPassword(), uInArr.getAddress());
                    break;
                }
            }

            Intent startIntent = new Intent(this, LoggedIn.class);
            startIntent.putExtra(MIN_ID, loggedInUser);
            startActivity(startIntent);
        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.wrong_input),Toast.LENGTH_SHORT).show();
        }


    }

    public void registerActivity(View view) {
        Intent startIntent = new Intent(this, Register_User.class);
        startActivity(startIntent);
    }
    public void show_dates_button(View view) {
        Intent startIntent = new Intent(this, Bare_se_tommedatoer.class);
        startActivity(startIntent);
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        if(userArray == null) {
             userArray = new ArrayList<>();
        } else {
            userArray.clear();
        }
        try {
           userArray = User.addToUserList(response);

        } catch(JSONException e) {

        }

    }


    /**
     *
     * Legger til alle brukerene i databasen i en array for å sjekke login
     *
     * */

    public void getUserList() {
        if(isOnline()) {
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, user_list, this, this);
            queue.add(stringRequest);
        } else { Toast.makeText(this, "Ingen nettverkstilgang. Kan ikke laste brukere", Toast.LENGTH_SHORT); }
    }
}
