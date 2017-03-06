package com.mertkilic.countries;

import android.os.Bundle;

import com.mertkilic.countries.base.BaseActivity;
import com.mertkilic.countries.databinding.ActivityCountryDetailBinding;
import com.mertkilic.countries.view.CountryDetailView;
import com.mertkilic.countries.viewmodel.CountryDetailViewModel;

public class CountryDetailActivity extends BaseActivity<ActivityCountryDetailBinding, CountryDetailViewModel>
        implements CountryDetailView {

    final static String EXTRA_COUNTRY_CODE = "code";

    public static Bundle getCountryDetailExtra(String code) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_COUNTRY_CODE, code);
        return bundle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_country_detail, savedInstanceState);
        try {
            String code = getIntent().getExtras().getString(EXTRA_COUNTRY_CODE);
            viewModel.getCountryDetail(code);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("You have to put some extras !!");
        }
    }

    @Override
    protected void initViewModel() {
        viewModel = new CountryDetailViewModel();
    }
}
