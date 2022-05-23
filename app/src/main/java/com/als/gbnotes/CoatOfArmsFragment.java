package com.als.gbnotes;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CoatOfArmsFragment extends Fragment {

    static private String ARG_INDEX = "index";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null){
            int index = arguments.getInt(ARG_INDEX);
            ImageView iv = view.findViewById(R.id.coast_of_arms_image_view);
            TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
            iv.setImageResource(images.getResourceId(index, 0));
            images.recycle();
        }
    }

    public static CoatOfArmsFragment newInstance(int index) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }
}
