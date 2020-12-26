package com.example.crimeapp;


import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID = "com.example.crimeapp.crimeID";

    public static Intent newIntent(Context context, UUID crimeID){

        Intent intent = new Intent(context,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeID);
        return intent;
    }

    @Override
    public Fragment CreateFragment() {

        //return new CrimeFragment();
        UUID crimeID = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeID);
    }




}