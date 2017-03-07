package com.mertkilic.countries.base;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mertkilic.countries.BR;

/**
 * Created by Mert Kilic on 3.3.2017.
 */

public abstract class BaseViewModel<V extends BaseView> extends BaseObservable {

    protected V view;
    protected String errorMessage;

    protected ObservableBoolean inProgress = new ObservableBoolean();
    protected ObservableBoolean error = new ObservableBoolean();

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

    public ObservableBoolean getInProgress() {
        return inProgress;
    }

    public ObservableBoolean getError() {
        return error;
    }

    @Bindable
    public String getErrorMessage() {
        return errorMessage;
    }

    protected void setFailure(boolean failure) {
        setFailure(failure, null);
    }

    protected void setFailure(boolean failure, String message) {
        inProgress.set(false);
        error.set(failure);
        errorMessage = message;
        notifyPropertyChanged(BR.errorMessage);
    }
}
