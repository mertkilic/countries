package com.mertkilic.countries.data.api;

import com.mertkilic.countries.data.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mert Kilic on 28.2.2017.
 */
public interface CountryApi {

    @GET("all")
    Call<List<Country>> getCountriesByFields(@Query("fields") String fields);

    @GET("alpha/{code}")
    Call<Country> getCountryByCode(@Path("code") String code);


}
