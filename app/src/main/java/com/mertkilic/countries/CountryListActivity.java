package com.mertkilic.countries;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.mertkilic.countries.adapter.CountryListAdapter;
import com.mertkilic.countries.base.BaseActivity;
import com.mertkilic.countries.databinding.ActivityCountryListBinding;
import com.mertkilic.countries.view.CountryView;
import com.mertkilic.countries.viewmodel.CountryViewModel;

public class CountryListActivity extends BaseActivity<ActivityCountryListBinding, CountryViewModel>
        implements CountryView {

    private CountryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_country_list, savedInstanceState);
        initCountryList();
    }

    @Override
    protected void initViewModel() {
        viewModel = new CountryViewModel();
    }

    @Override
    public void onCountriesLoaded() {
        adapter.notifyDataSetChanged();
    }

    private void initCountryList() {
        binding.listCountry.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountryListAdapter(viewModel);
        binding.listCountry.setAdapter(adapter);
    }
}
