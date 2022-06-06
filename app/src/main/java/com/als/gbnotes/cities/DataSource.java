package com.als.gbnotes.cities;

import com.als.gbnotes.City;
import com.als.gbnotes.R;

import java.util.ArrayList;

public class DataSource implements IDataSource {
    private ArrayList<City> list = new ArrayList();

    public DataSource() {
        initList();
    }

    private void initList() {
        list.add(new City(R.drawable.msc,"Moskow"));
        list.add(new City(R.drawable.spb,"Sankt-piterburg"));
        list.add(new City(R.drawable.ebrg,"Ekaterinburg"));
        list.add(new City(R.drawable.nsk,"Новосибирск"));
        list.add(new City(R.drawable.sam,"Samara"));
        list.add(new City(R.drawable.no_image,"Ufa"));
        list.add(new City(R.drawable.no_image,"Omsk"));
    }

    @Override
    public ArrayList<City> getCities() {
        return list;
    }

    @Override
    public void addCity(City city) {
        list.add(city);
    }

    @Override
    public void updateCity(City city, int position) {
        list.remove(position);
        list.add(position, city);
    }

    @Override
    public void deleteCity(int position) {
        list.remove(position);
    }

    @Override
    public void removeAll() {
        list.clear();
    }
}
