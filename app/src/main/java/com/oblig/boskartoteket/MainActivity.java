package com.oblig.boskartoteket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity{
    private EditText user_input, password_input, register_email, register_password;
    private final String MIN_ID = "boskartoteket-app-v1";
    private Button login_button, register_button, show_dates_button;
    private BottomNavigationView bottomView;
    private Dialog register_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        user_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        show_dates_button = findViewById(R.id.show_dates_button);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_dialog = new Dialog(MainActivity.this, R.style.Theme_Design_NoActionBar);
                register_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                register_dialog.setContentView(R.layout.register_popup);
                register_dialog.setCancelable(true);
                register_dialog.show();
            }
        });
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                URL addressAPI;
                try {
                    addressAPI = new URL("ws.geonorge.no/adresser/v1");
                    HttpsURLConnection conn = (HttpsURLConnection) addressAPI.openConnection();
                    conn.setRequestProperty("User-Agent", MIN_ID);

                    if(conn.getResponseCode() == 200) {
                        InputStream responeBody = conn.getInputStream();
                        InputStreamReader responeBodyReader = new InputStreamReader(responeBody, StandardCharsets.UTF_8);
                    } else {
                        // Something
                    }


                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void log_in_user(View view) {
        Resources res = getResources();
        String[] user = res.getStringArray(R.array.user_simon);

        if(user_input.getText().toString().equals(user[0]) && password_input.getText().toString().equals(user[1])) {
            Toast.makeText(getApplicationContext(),user_input.getText(),Toast.LENGTH_SHORT).show();

            Intent startIntent = new Intent(this, LoggedIn.class);
            startActivity(startIntent);
        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.wrong_input),Toast.LENGTH_SHORT).show();
        }

    }


    public void dismissPopup(View view) {
        register_dialog.dismiss();

    }

    public void register_user(View view) {
        // Making a new user
        // User newUser = new User(register_email.getText().toString(), register_password.getText().toString());
        //TODO : Store in DB.
        // For now dismiss the popup
        register_dialog.dismiss();
    }

    public void show_dates_button(View view) {
        Intent startIntent = new Intent(this, Bare_se_tommedatoer.class);
        startActivity(startIntent);
    }
}
