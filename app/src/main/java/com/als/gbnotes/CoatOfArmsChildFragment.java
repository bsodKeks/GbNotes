package com.als.gbnotes;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CoatOfArmsChildFragment extends Fragment {
    static final String ARG_INDEX_CHILD = "index";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_coat_of_arms, container,
                false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            City city = arguments.getParcelable(ARG_INDEX_CHILD);
            TypedArray images =
                    getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
            ((ImageView)
                    view.findViewById(R.id.coat_of_arms_image_view)).setImageResource(images.getResourceId(city.getImageIndex(), 0));
            images.recycle();
        }
        Button btn = view.findViewById(R.id.coat_of_arms_child_button_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
    }
    public static CoatOfArmsChildFragment newInstance(City city) {
        CoatOfArmsChildFragment fragment = new CoatOfArmsChildFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX_CHILD, city);
        fragment.setArguments(args);
        return fragment;
    }
}
