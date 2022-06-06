package com.als.gbnotes.cities;

import android.view.View;

import com.als.gbnotes.City;

public interface CitiesClickListener {
    void onImageClick(City city);
    void onTextViewClick(int position);
    void onLongItemClick(View view, int position);
}
