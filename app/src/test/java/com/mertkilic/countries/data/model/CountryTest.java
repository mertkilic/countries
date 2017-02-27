package com.mertkilic.countries.data.model;

import com.bluelinelabs.logansquare.LoganSquare;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Mert Kilic on 27.2.2017.
 */

public class CountryTest {

    @Test
    public void parseCountryFromJson() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/af.json");
            Country country = LoganSquare.parse(inputStream, Country.class);
            assertNotNull(country.getRegion());
            assertNotNull(country.getName());
            assertNotNull(country.getCapital());
            assertNotNull(country.getCode());
            assertNotEquals(0, country.getPopulation());
            assertNotEquals(0, country.getArea());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
