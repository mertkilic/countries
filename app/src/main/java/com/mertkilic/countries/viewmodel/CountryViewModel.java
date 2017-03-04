package com.mertkilic.countries.viewmodel;

import com.mertkilic.countries.base.BaseViewModel;
import com.mertkilic.countries.data.model.Country;
import com.mertkilic.countries.view.CountryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mert Kilic on 4.3.2017.
 */

public class CountryViewModel extends BaseViewModel<CountryView> {

    private List<Country> countries = new ArrayList<>();
}
