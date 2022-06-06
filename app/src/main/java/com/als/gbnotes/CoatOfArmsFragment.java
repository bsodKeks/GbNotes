package com.als.gbnotes;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CoatOfArmsFragment extends Fragment {

    static private String ARG_INDEX = "index";
    private DatePicker datePicker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem item = menu.findItem(R.id.action_exit);
        if (item != null){
            item.setVisible(false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            City city = arguments.getParcelable(ARG_INDEX);
            TextView tvCity = view.findViewById(R.id.coat_of_arms_text_view);
            tvCity.setText(city.getCityName());

            getChildFragmentManager().beginTransaction()
                    .replace(R.id.coat_of_arms_child_container, CoatOfArmsChildFragment.newInstance(city))
                    .addToBackStack("hgjghj")
                    .commit();
        }
        datePicker = view.findViewById(R.id.datePicker);
        Button btnBack = view.findViewById(R.id.coat_of_arms_button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //requireActivity().getSupportFragmentManager().popBackStack();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, datePicker.getYear());
                calendar.set(Calendar.MONTH, datePicker.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                Toast.makeText(requireContext(), getDateFromCalendar(calendar), Toast.LENGTH_SHORT).show();
            }
        });

        Button btnRemove = view.findViewById(R.id.coat_of_arms_button_remove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentManager fm = requireActivity().getSupportFragmentManager();
                final List<Fragment> fragments = fm.getFragments();
                for (Fragment fragment : fragments) {
                    if (fragment instanceof CoatOfArmsFragment && fragment.isVisible()) {
                        fm.beginTransaction()
                                .remove(fragment)
                                .commit();
                    }
                }
            }
        });
    }

    private String getDateFromCalendar(Calendar cal){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
        Date date = cal.getTime();
        return dateFormat.format(date);
    }

    public static CoatOfArmsFragment newInstance(City city) {
        CoatOfArmsFragment fragment = new CoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX, city);
        fragment.setArguments(args);
        return fragment;
    }
}
