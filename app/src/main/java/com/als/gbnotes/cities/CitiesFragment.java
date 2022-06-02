package com.als.gbnotes.cities;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.als.gbnotes.City;
import com.als.gbnotes.CoatOfArmsFragment;
import com.als.gbnotes.R;

import java.util.ArrayList;

public class CitiesFragment extends Fragment {
    private final String CURRENT_CITY = "CURRENT_CITY";
    private City city = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            city = savedInstanceState.getParcelable(CURRENT_CITY);
        }
        initList(view);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_CITY, city);
        super.onSaveInstanceState(outState);
    }

    private void initList(View view) {
        ArrayList<City> list = new ArrayList();
        String[] cities = getResources().getStringArray(R.array.cities);
        TypedArray images =
                getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
        for (int i = 0; i < cities.length; i++) {
            City city = new City(images.getResourceId(i, 0), cities[i]);
            list.add(city);
        }
        RecyclerView rv = view.findViewById(R.id.rvCities);
        //GridLayoutManager llm = new GridLayoutManager(getContext(), 2);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        DividerItemDecoration decorator = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        decorator.setDrawable((getResources().getDrawable(R.drawable.separator,
                null)));
        rv.addItemDecoration(decorator);

        CitiesAdapter adapter = new CitiesAdapter();
        adapter.setList(list);
        adapter.setListener(new CitiesClickListener() {
            @Override
            public void onImageClick(City city) {
                CitiesFragment.this.city = city;
                showCoatOfArms(city);
            }

            @Override
            public void onTextViewClick(int position) {
                Toast.makeText(getContext(), "position: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        rv.setAdapter(adapter);
    }


    private void showCoatOfArms(City city) {
        showPortraitCoastOfArms(city);

    }

    private void showPortraitCoastOfArms(City city) {
        CoatOfArmsFragment coatOfArmsFragment = CoatOfArmsFragment.newInstance(city);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, coatOfArmsFragment)
                .addToBackStack("coat")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}