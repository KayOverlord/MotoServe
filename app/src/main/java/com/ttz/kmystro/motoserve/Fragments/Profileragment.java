package com.ttz.kmystro.motoserve.Fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ttz.kmystro.motoserve.R;

public class Profileragment extends android.support.v4.app.Fragment {

    public Profileragment newInstance() {
        // Required empty public constructor

        return new Profileragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profileragment, container, false);
    }

}
