package com.example.country_learner;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

// Used for the favourites activity.

public class CountryRecycleAdapter  extends RecyclerView.Adapter<CountryRecycleAdapter.ViewHolder>{
     ArrayList<Country> countries;
    private Context context;

    interface CountryClickListener{
        void onCountryClicked(Country selectedCountry);
    }
    CountryClickListener listener;

    public CountryRecycleAdapter(ArrayList<Country> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_country, parent, false);

        return new CountryRecycleAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryRecycleAdapter.ViewHolder holder, int position) {
        Country thisCountry = countries.get(position);
        holder.countryName.setText(thisCountry.getOfficialName());
        holder.countryCommonName.setText("Common name: " + thisCountry.getCommonName());
        Log.d("Dude", thisCountry.getRegion());

        //Flag needs to fetch from internet
        Glide.with(context).load(thisCountry.getFlag()).into(holder.flagView);

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView countryName;
        public TextView countryCommonName;


        public ImageView flagView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.countryname);
            countryCommonName = itemView.findViewById(R.id.textview_commonName);
            flagView = itemView.findViewById(R.id.country_flag);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onCountryClicked(countries.get(getAdapterPosition()));
                }
            });
        }
    }
}
