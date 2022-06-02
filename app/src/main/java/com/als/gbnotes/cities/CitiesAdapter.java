package com.als.gbnotes.cities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.als.gbnotes.City;
import com.als.gbnotes.R;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {

    private List<City> list;

    private CitiesClickListener listener;

    public void setListener(CitiesClickListener listener) {
        this.listener = listener;
    }

    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_grid, parent, false);
        return new CityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.getItemView().<TextView>findViewById(R.id.textView).setText(list.get(position).getCityName());
        holder.getItemView().<ImageView>findViewById(R.id.imageView).setImageResource(list.get(position).getImageIndex());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemView.findViewById(R.id.cv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    City city = list.get(getAdapterPosition());
                    listener.onImageClick(city);
                }
            });

            itemView.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTextViewClick(getAdapterPosition());
                }
            });
        }

        public View getItemView() {
            return itemView;
        }
    }
}
