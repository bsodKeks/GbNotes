package com.als.gbnotes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CitiesFragment citiesFragment = new CitiesFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, citiesFragment)
                .commit();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            CoatOfArmsFragment coaf = CoatOfArmsFragment.newInstance(0);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.coat_of_arms_container, coaf)
                    .commit();
        }

    }
}