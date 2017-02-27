package com.mertkilic.countries.data.model;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by Mert Kilic on 27.2.2017.
 */
@JsonObject
public class Country {
    private String name;
    private String capital;
    private String region;
    private long population;
    private double area;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}