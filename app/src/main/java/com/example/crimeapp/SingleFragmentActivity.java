package com.example.crimeapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity {


    public abstract Fragment CreateFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        FragmentManager managerFragment = getSupportFragmentManager();

        Fragment fragment = managerFragment.findFragmentById(R.id.fragment_container);


        if (fragment==null){

            fragment=CreateFragment();
            managerFragment.beginTransaction().replace(R.id.fragment_container,fragment).commit();
        }
    }




}
