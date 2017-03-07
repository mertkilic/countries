package com.mertkilic.countries;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.mertkilic.countries.base.BaseActivity;
import com.mertkilic.countries.databinding.ActivityCountryDetailBinding;
import com.mertkilic.countries.view.CountryDetailView;
import com.mertkilic.countries.viewmodel.CountryDetailViewModel;

public class CountryDetailActivity extends BaseActivity<ActivityCountryDetailBinding, CountryDetailViewModel>
        implements CountryDetailView, SwipeRefreshLayout.OnRefreshListener {

    final static String EXTRA_COUNTRY_CODE = "code";

    private String code;

    public static Bundle getCountryDetailExtra(String code) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_COUNTRY_CODE, code);
        return bundle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_country_detail, savedInstanceState);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.refreshLayout.setOnRefreshListener(this);
        try {
            code = getIntent().getExtras().getString(EXTRA_COUNTRY_CODE);
            viewModel.getCountryDetail(code);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("You have to put some extras !!");
        }
    }

    @Override
    protected void initViewModel() {
        viewModel = new CountryDetailViewModel();
    }

    @Override
    public Resources getResourcez() {
        return getResources();
    }

    @Override
    public void onRefresh() {
        viewModel.getCountryDetail(code);
    }
}
