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
        user_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);


    }


    public void log_in_user(View view) {
        Resources res = getResources();
        String[] user = res.getStringArray(R.array.user_simon);

        if(user_input.getText().toString().equals(user[0]) && password_input.getText().toString().equals(user[1])) {
            Intent startIntent = new Intent(this, LoggedIn.class);
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



}
