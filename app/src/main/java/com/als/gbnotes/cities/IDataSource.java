package com.als.gbnotes.cities;

import com.als.gbnotes.City;

import java.util.ArrayList;
import java.util.List;

public interface IDataSource {
    ArrayList<City> getCities();
    void addCity(City city);
    void updateCity(City city, int position);
    void deleteCity(int position);
    void removeAll();
}
