package com.mertkilic.countries.viewmodel;

import android.util.SparseArray;

import com.mertkilic.countries.R;
import com.mertkilic.countries.base.BaseViewModel;
import com.mertkilic.countries.data.api.CountryService;
import com.mertkilic.countries.data.model.Country;
import com.mertkilic.countries.view.CountryView;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mert Kilic on 4.3.2017.
 */

public class CountryViewModel extends BaseViewModel<CountryView> {

    private SparseArray<CountryItemViewModel> itemViewModels = new SparseArray<>();
    private List<Country> countries = new ArrayList<>();
    private CountryService service = CountryService.getInstance();

    public CountryViewModel() {
        loadCountries();
    }

    public CountryItemViewModel getItemViewModel(int position) {
        CountryItemViewModel itemViewModel = itemViewModels.get(position);
        if (itemViewModel == null) {
            try {
                itemViewModel = new CountryItemViewModel(this, countries.get(position));
            } catch (IndexOutOfBoundsException e) {
                itemViewModel = new CountryItemViewModel(this, new Country());
            }
            itemViewModels.put(position, itemViewModel);
        }
        return itemViewModel;
    }

    public void loadCountries() {
        inProgress.set(true);
        service.getApi()
                .getCountriesByFields(service.generateCountryApiFields("name", "alpha2Code"))
                .enqueue(new Callback<List<Country>>() {
                    @Override
                    public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                        setFailure(false);
                        countries = response.body();
                        view.onCountriesLoaded();
                    }

                    @Override
                    public void onFailure(Call<List<Country>> call, Throwable t) {
                        String message;
                        if (t instanceof UnknownHostException)
                            message = view.getResourcez().getString(R.string.error_internet);
                        else
                            message = view.getResourcez().getString(R.string.error_something);
                        setFailure(true, message);
                    }
                });
    }

    public int getCount() {
        return countries.size();
    }

    public void showCountryDetail(String code) {
        view.showCountryDetail(code);
    }

}
