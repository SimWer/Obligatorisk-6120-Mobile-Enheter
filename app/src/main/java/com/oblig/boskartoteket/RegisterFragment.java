package com.oblig.boskartoteket;

import androidx.fragment.app.DialogFragment;

public class RegisterFragment extends DialogFragment {

    public RegisterFragment() {
        //Empty constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment registerFragment = new RegisterFragment();
        return registerFragment;
    }


}