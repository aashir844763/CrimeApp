package com.example.crimeapp;


import androidx.fragment.app.Fragment;


public class CrimeListActivity extends SingleFragmentActivity {


    @Override
    public Fragment CreateFragment() {
        return new CrimeListFragment();
    }
}
