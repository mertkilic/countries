package com.mertkilic.countries.viewmodel;

import com.mertkilic.countries.base.BaseItemViewModel;
import com.mertkilic.countries.data.model.Country;

/**
 * Created by Mert Kilic on 4.3.2017.
 */

public class CountryItemViewModel extends BaseItemViewModel<Country> {
    private CountryViewModel countryViewModel;

    public CountryItemViewModel(CountryViewModel countryViewModel, Country country) {
        this.countryViewModel = countryViewModel;
        this.model = country;
    }

    public String getName(){
        return model.getName();
    }

    public String getCode(){
        return model.getCode();
    }
}
