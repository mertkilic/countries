package com.mertkilic.countries.data.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.mertkilic.countries.util.Constants;

/**
 * Created by Mert Kilic on 27.2.2017.
 */
@JsonObject
public class Country {

    private final String GOOGLE_STATIC_MAP_URL =
            "https://maps.googleapis.com/maps/api/staticmap?center=latlng&zoom=10&size=800x400";

    @JsonField
    private String name;
    @JsonField
    private String capital;
    @JsonField
    private String region;
    @JsonField(name = "alpha2Code")
    private String code;
    @JsonField
    private long population;
    @JsonField
    private double area;
    @JsonField
    private float[] latlng;

    public Country() {
        latlng = new float[2];
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float[] getLatlng() {
        return latlng;
    }

    public void setLatlng(float[] latlng) {
        this.latlng = latlng;
    }

    public String getStaticMapUrl(){
        return GOOGLE_STATIC_MAP_URL.replace("latlng", latlng[0] + "," + latlng[1]);
    }
}
