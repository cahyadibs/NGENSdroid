package com.example.ngensdroid.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ngensdroid.LoginActivity;
import com.example.ngensdroid.ProfileActivity;
import com.example.ngensdroid.R;
import com.example.ngensdroid.SharedPrefManager;


public class ProfileFragment extends Fragment{

    TextView textViewId, textViewUsername, textViewEmail, textViewGender;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        return view;

    }
/*
    public void onViewCreated(View view, Bundle savedInstanceState){
        textViewId = (TextView) view.findViewById(R.id.textViewId);
        textViewUsername = (TextView) view.findViewById(R.id.textViewUsername);
        textViewEmail = (TextView) view.findViewById(R.id.textViewEmail);
        textViewGender = (TextView) view.findViewById(R.id.textViewGender);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
    */

}
