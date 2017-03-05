package com.mertkilic.countries.base;

/**
 * Created by Mert Kilic on 4.3.2017.
 */

public class BaseItemViewModel<T> extends BaseViewModel {
    protected T model;

    public void setModel(T model) {
        this.model = model;
    }
}
