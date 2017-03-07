package com.mertkilic.countries;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.mertkilic.countries.adapter.CountryListAdapter;
import com.mertkilic.countries.base.BaseActivity;
import com.mertkilic.countries.databinding.ActivityCountryListBinding;
import com.mertkilic.countries.view.CountryView;
import com.mertkilic.countries.viewmodel.CountryViewModel;

public class CountryListActivity extends BaseActivity<ActivityCountryListBinding, CountryViewModel>
        implements CountryView, SwipeRefreshLayout.OnRefreshListener {

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

    @Override
    public void showCountryDetail(String code) {
        Intent intent = new Intent(this, CountryDetailActivity.class);
        intent.putExtras(CountryDetailActivity.getCountryDetailExtra(code));
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        viewModel.loadCountries();
    }

    @Override
    public Resources getResourcez() {
        return getResources();
    }

    private void initCountryList() {
        binding.refreshLayout.setOnRefreshListener(this);
        binding.listCountry.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountryListAdapter(viewModel);
        binding.listCountry.setAdapter(adapter);
    }
}
