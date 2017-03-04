package com.mertkilic.countries;

import android.os.Bundle;

import com.mertkilic.countries.base.BaseActivity;
import com.mertkilic.countries.databinding.ActivityCountryListBinding;
import com.mertkilic.countries.view.CountryView;
import com.mertkilic.countries.viewmodel.CountryViewModel;

public class CountryListActivity extends BaseActivity<ActivityCountryListBinding, CountryViewModel>
        implements CountryView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViewModel() {
        viewModel = new CountryViewModel();
    }
}
