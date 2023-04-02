package com.example.country_learner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Locale;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private Country displayedCountry;
    private Context context;


    public CountryAdapter(Country country, Context context) {
        this.displayedCountry = country;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_country_information, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.countryName.setText(displayedCountry.getOfficialName());
        holder.commonName.setText("Commonly known as: " + displayedCountry.getCommonName());
        holder.capital.setText("Capital City: " + displayedCountry.getCapital());
        holder.languages.setText(displayedCountry.getOfficialLanguages());
        holder.region.setText(displayedCountry.getRegion());
        holder.subregion.setText(displayedCountry.getSubregion());

        //Adds commas per thousand
        holder.population.setText(
            NumberFormat.getNumberInstance(Locale.US).format(displayedCountry.getPopulation())
        );

        holder.currency.setText(displayedCountry.getCurrency());
        holder.symbol.setText(displayedCountry.getCurrencySymbol());
        holder.independent.setText(displayedCountry.isIndependent() ? "✅" : "❌");
        holder.unMember.setText(displayedCountry.isUnMember() ? "✅" : "❌");

        //Flag needs to fetch from internet
        Glide.with(context).load(displayedCountry.getFlag()).into(holder.flagView);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView countryName, commonName, capital, languages,
                region, subregion, population, currency, symbol, independent, unMember;
        public ImageView flagView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.view_countryName);
            commonName = itemView.findViewById(R.id.view_commonName);
            capital = itemView.findViewById(R.id.view_capitalCity);
            languages = itemView.findViewById(R.id.view_languages);
            region = itemView.findViewById(R.id.view_continent);
            subregion = itemView.findViewById(R.id.view_subregion);
            population = itemView.findViewById(R.id.view_population);
            currency = itemView.findViewById(R.id.view_currency);
            symbol = itemView.findViewById(R.id.view_symbol);
            independent = itemView.findViewById(R.id.view_independent);
            unMember = itemView.findViewById(R.id.view_unMember);
            flagView = itemView.findViewById(R.id.imageView2);
        }
    }
}