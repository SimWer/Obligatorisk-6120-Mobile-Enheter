package com.oblig.boskartoteket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Bare_se_tommedatoer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bare_se_tommedatoer);
    }

    public void return_main(View view) {
        Intent returnIntent = new Intent(this, MainActivity.class);
        startActivity(returnIntent);
        finish();
    }
}
