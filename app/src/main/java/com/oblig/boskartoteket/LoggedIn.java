package com.oblig.boskartoteket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class LoggedIn extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Fragment fragment;
    private String fullName;
    private User loggedInUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        //Getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.nav_view);

        navigation.setOnNavigationItemSelectedListener(this);

        // Får med hele user objektet slik at det kan brukes videre

        Intent intent = getIntent();
        loggedInUser = (User)intent.getSerializableExtra(MainActivity.MIN_ID);

        if(savedInstanceState == null) {
            fragment = new HjemFragment();
            // Viktig med denne for å ikke få NPE
            if (loggedInUser != null) {
                fragment = HjemFragment.newInstance(loggedInUser);

            }
            loadFragment(fragment);


        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId()) {
            case R.id.menu_dates:
                fragment = new HjemFragment();
                fragment = HjemFragment.newInstance(loggedInUser);
                break;
            case R.id.menu_shopping_cart:
                fragment = new BestillingFragment();
                break;
            case R.id.menu_settings:
                fragment = new User_Settings();
                fragment = User_Settings.newInstance(loggedInUser);
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //Switching Fragment
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    public void logOut(View view) {
        finish();
    }


    /**
     *
     * Metode for å legge til varer i handlekurven
     *
     * */
    public void addToCart(View view) {
        int id = view.getId();

        //TODO Implementer ferdig
        switch (id) {
            case R.id.add_bin_button: break;
            case R.id.add_organic_button: break;
            case R.id.add_plastic_button: break;
            case R.id.add_rest_button: break;
            default: break;
        }
    }

    public void getProductList(){

    }


}
