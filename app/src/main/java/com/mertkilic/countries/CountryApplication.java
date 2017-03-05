package com.mertkilic.countries;

import android.app.Application;

import com.mertkilic.countries.data.api.CountryService;
import com.mertkilic.countries.util.Constants;
import com.mertkilic.countries.util.Utils;

import java.io.File;

/**
 * Created by Mert Kilic on 28.2.2017.
 */

public class CountryApplication extends Application {

    private static CountryApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        CountryApplication.application = this;
        File cache = new File(getFilesDir(), Constants.CACHE_NAME);
        if(!cache.exists()){
            cache.mkdir();
        }
        Constants.CACHE_DIR = cache.getAbsolutePath();
    }

    public static boolean isConnected(){
        return Utils.isConnected(application.getApplicationContext());
    }

}
