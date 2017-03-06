package com.mertkilic.countries.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mertkilic.countries.databinding.ItemCountryBinding;
import com.mertkilic.countries.viewmodel.CountryItemViewModel;
import com.mertkilic.countries.viewmodel.CountryViewModel;

/**
 * Created by Mert Kilic on 4.3.2017.
 */

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private CountryViewModel viewModel;

    public CountryListAdapter(CountryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCountryBinding binding =
                ItemCountryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CountryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        CountryItemViewModel itemViewModel = viewModel.getItemViewModel(position);
        holder.bindData(itemViewModel);
    }

    @Override
    public int getItemCount() {
        return viewModel.getCount();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        ItemCountryBinding binding;

        public CountryViewHolder(ItemCountryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindData(CountryItemViewModel model) {
            binding.setViewModel(model);
        }

    }
}
