package com.oblig.boskartoteket;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class User_Settings extends Fragment {
    private View v;
    private EditText fName, lName, address, email, password;
    private User user;
    private Button updateBtn;
    private RestAdapter restAdapter;

    public User_Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restAdapter = new RestAdapter(getContext());
        v = inflater.inflate(R.layout.fragment_user__settings, container, false);
        fName = v.findViewById(R.id.edit_text_first_name);
        lName = v.findViewById(R.id.edit_text_last_name);
        address = v.findViewById(R.id.edit_text_street_name);
        email = v.findViewById(R.id.edit_text_email);
        password = v.findViewById(R.id.edit_text_password);
        updateBtn = v.findViewById(R.id.updateButton);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeUserInfo();
            }
        });

        //Endrer brukerinfo

        if(getArguments() != null) {
            user = (User)getArguments().getSerializable(MainActivity.MIN_ID);
            fName.setText(user.getfNavn());
            lName.setText(user.geteNavn());
            address.setText(user.getAddress());
            email.setText(user.getEmail());
            password.setText(user.getPassword());
        }
        // Inflate the layout for this fragment
        return v;
    }

    /***
     *
     * Metode for å lage en ny instance av fragmentet, sender med brukerdata for å bruke videre
     *
     * */

    public static User_Settings newInstance(User user) {
        User_Settings userFragment  = new User_Settings();
        Bundle arguments = new Bundle();
        arguments.putSerializable(MainActivity.MIN_ID, user);
        userFragment.setArguments(arguments);
        return userFragment;
    }

    /***
     *
     * Metode for å oppdatere brukerinfo
     *
     *
     * // TODO: 08.10.2019 Ikke ferdig
     * */

    public void changeUserInfo() {
        User newUser = new User(fName.getText().toString(), lName.getText().toString(), email.getText().toString(), password.getText().toString(), address.getText().toString());
        restAdapter.updateUser(newUser, user.getId());
    }

}
