package com.als.gbnotes.cities;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.als.gbnotes.City;
import com.als.gbnotes.CoatOfArmsFragment;
import com.als.gbnotes.R;
import com.als.gbnotes.activity.IDataSourceHandler;

import java.util.ArrayList;

public class CitiesFragment extends Fragment {
    private final String CURRENT_CITY = "CURRENT_CITY";
    private City city = null;
    private RecyclerView rv;
    private CitiesAdapter adapter = new CitiesAdapter();
    private int aminDelay = 1000;
    private IDataSource dataSource;

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
        dataSource = ((IDataSourceHandler)getActivity()).getDataSource();
        view.findViewById(R.id.fab).setOnClickListener(v -> {
           addCity();
//           removeAllCity();

        });
        initList(view);
    }

    private void addCity(){
        City city = new City(R.drawable.no_image, "New-City");
        dataSource.addCity(city);
        adapter.notifyItemInserted(dataSource.getCities().size() - 1);
        rv.scrollToPosition(dataSource.getCities().size() - 1);
    }

    private void removeAllCity(){
       dataSource.removeAll();
       adapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_CITY, city);
        super.onSaveInstanceState(outState);
    }

    private void initList(View view) {
        ArrayList<City> list = dataSource.getCities();
        rv = view.findViewById(R.id.rvCities);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(aminDelay);
        animator.setChangeDuration(aminDelay);
        animator.setRemoveDuration(aminDelay);
        rv.setItemAnimator(animator);
        GridLayoutManager llm = new GridLayoutManager(getContext(), 2);
        //LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        DividerItemDecoration decorator = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        decorator.setDrawable((getResources().getDrawable(R.drawable.separator,
                null)));
        rv.addItemDecoration(decorator);

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

            @Override
            public void onLongItemClick(View view, int position) {
                Activity activity = requireActivity();
                PopupMenu popupMenu = new PopupMenu(activity, view);
                activity.getMenuInflater().inflate(R.menu.menu_cities_context, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_update:
                                int image = adapter.getList().get(position).getImageIndex();
                                City city = new City(image, "updated");
                                dataSource.updateCity(city, position);
                                adapter.notifyItemChanged(position);
                                return true;
                            case R.id.action_delete:
                                dataSource.deleteCity(position);
                                adapter.notifyItemRemoved(position);
                                return true;
                            case R.id.action_date:

                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.show();
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