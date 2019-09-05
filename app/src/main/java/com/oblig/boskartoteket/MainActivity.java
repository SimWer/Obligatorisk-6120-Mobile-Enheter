package com.oblig.boskartoteket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText user_input, password_input;
    private Button login_button;
    private BottomNavigationView bottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        user_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        login_button = findViewById(R.id.login_button);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_button) {
            login_button.setText(R.string.buy_text);
        }

    }

    public void log_in_user(View view) {
        Resources res = getResources();
        String[] user = res.getStringArray(R.array.user_simon);

        if(user_input.getText().toString().equals(user[0]) && password_input.getText().toString().equals(user[1])) {
            Toast.makeText(getApplicationContext(),user_input.getText(),Toast.LENGTH_SHORT).show();

            Intent startIntent = new Intent(this, LoggedIn.class);
            startActivity(startIntent);
        } else {
            Toast.makeText(getApplicationContext(),user_input.getText().toString() + user[0],Toast.LENGTH_SHORT).show();
        }

    }
}
