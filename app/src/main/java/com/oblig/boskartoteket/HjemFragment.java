package com.oblig.boskartoteket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HjemFragment extends Fragment {
    private TextView name, address;
    private User user;
    private String nameString;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_logged_in, null);
        name = v.findViewById(R.id.user_full_name);
        address = v.findViewById(R.id.address);
        if(getArguments() != null) {
            user = (User)getArguments().getSerializable(MainActivity.MIN_ID);
            String fullname = user.getfNavn() + " " + user.geteNavn();
            String addressString = user.getAddress();
            name.setText(fullname);
            address.setText(addressString);

        }
        return v;
    }

    /***
     *
     * Metode for å lage en ny instance av fragmentet, sender med brukerdata for å bruke videre
     *
     * */

    public static HjemFragment newInstance(User user) {
        HjemFragment fragment = new HjemFragment();
        Bundle arguments =  new Bundle();
        arguments.putSerializable(MainActivity.MIN_ID, user);
        fragment.setArguments(arguments);
        return fragment;
    }
}
