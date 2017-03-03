package com.mertkilic.countries.base;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Mert Kilic on 3.3.2017.
 */

public abstract class BaseViewModel<V extends BaseView> extends BaseObservable {

    protected V view;

    public V getView() {
        return view;
    }

    public void attachView(@NonNull V view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
        }
    }

    public void detachView() {
        this.view = null;
    }

    public void saveInstanceState(@NonNull Bundle outState) {
    }

    public void restoreInstanceState(@NonNull Bundle savedInstanceState) {
    }
}
