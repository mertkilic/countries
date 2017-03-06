package com.mertkilic.countries.viewmodel;

import android.databinding.Bindable;

import com.mertkilic.countries.BR;
import com.mertkilic.countries.base.BaseViewModel;
import com.mertkilic.countries.data.api.CountryService;
import com.mertkilic.countries.data.model.Country;
import com.mertkilic.countries.view.CountryDetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mert Kilic on 5.3.2017.
 */

public class CountryDetailViewModel extends BaseViewModel<CountryDetailView> {

    private CountryService countryService = CountryService.getInstance();
    private Country country = new Country();

    public void getCountryDetail(String code) {
        countryService.getApi().getCountryByCode(code).enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                country = response.body();
                notifyPropertyChanged(BR.country);
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {

            }
        });
    }

    @Bindable
    public Country getCountry(){
        return country;
    }
}
