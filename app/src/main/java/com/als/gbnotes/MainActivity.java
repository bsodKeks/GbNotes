package com.als.gbnotes;

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
                .add(R.id.fragment_container, citiesFragment)
                .commit();
    }
}