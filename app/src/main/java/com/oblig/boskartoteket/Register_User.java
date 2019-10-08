package com.oblig.boskartoteket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;


public class Register_User extends AppCompatActivity {

    private EditText fname, lname, email, password, address;
    private User user;
    private RestAdapter restAdapter;

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
}
