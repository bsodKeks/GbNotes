package com.als.gbnotes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CitiesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(29);
            layoutView.addView(tv);
            final int position = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showCoastOfArms(position);
                }
            });
        }
    }

    private void showCoastOfArms(int position) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showLandCoastOfArms(position);
        } else {
            showPortraitCoastOfArms(position);
        }

    }

    private void showLandCoastOfArms(int position) {
        CoatOfArmsFragment coatOfArmsFragment = CoatOfArmsFragment.newInstance(position);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.coat_of_arms_container, coatOfArmsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private void showPortraitCoastOfArms(int position) {
        CoatOfArmsFragment coatOfArmsFragment = CoatOfArmsFragment.newInstance(position);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, coatOfArmsFragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}