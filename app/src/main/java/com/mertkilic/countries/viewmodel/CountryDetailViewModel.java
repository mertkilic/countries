package com.mertkilic.countries.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

import com.mertkilic.countries.BR;
import com.mertkilic.countries.R;
import com.mertkilic.countries.base.BaseViewModel;
import com.mertkilic.countries.data.api.CountryService;
import com.mertkilic.countries.data.model.Country;
import com.mertkilic.countries.view.CountryDetailView;

import java.net.UnknownHostException;

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
        inProgress.set(true);
        countryService.getApi().getCountryByCode(code).enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                setFailure(false);
                country = response.body();
                notifyPropertyChanged(BR.country);
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                String message;
                if (t instanceof UnknownHostException)
                    message = view.getResourcez().getString(R.string.error_internet);
                else
                    message = view.getResourcez().getString(R.string.error_something);
                setFailure(true, message);
            }
        });
    }

    @Bindable
    public Country getCountry() {
        return country;
    }
}
