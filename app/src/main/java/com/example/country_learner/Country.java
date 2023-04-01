package com.example.country_learner;

public class Country {
    private String officialName;
    private String commonName;
    private String capital;
    private String officialLanguages;
    private String continent;
    private String subregion;
    private String currency;
    private String currencySymbol;
    private int population;
    private boolean isIndependent, isUnMember;

    public Country() {
        this.officialName = "N/A";
        this.commonName = "N/A";
        this.capital = "N/A";
        this.officialLanguages = "N/A";
        this.continent = "N/A";
        this.subregion = "N/A";
        this.currency = "N/A";
        this.currencySymbol = "N/A";
        this.population = -1;
        this.isIndependent = false;
        this.isUnMember = false;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getOfficialLanguages() {
        return officialLanguages;
    }

    public void setOfficialLanguages(String officialLanguages) {
        this.officialLanguages = officialLanguages;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isIndependent() {
        return isIndependent;
    }

    public void setIndependent(boolean independent) {
        isIndependent = independent;
    }

    public boolean isUnMember() {
        return isUnMember;
    }

    public void setUnMember(boolean unMember) {
        isUnMember = unMember;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }


}