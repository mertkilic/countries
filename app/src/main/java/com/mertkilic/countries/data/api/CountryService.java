package com.mertkilic.countries.data.api;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.mertkilic.countries.BuildConfig;
import com.mertkilic.countries.CountryApplication;
import com.mertkilic.countries.util.Constants;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

/**
 * Created by Mert Kilic on 28.2.2017.
 */
public class CountryService {
    private static CountryService instance = new CountryService();

    private CountryService() {
    }

    public static CountryService getInstance() {
        return instance;
    }

    public CountryApi getApi() {
        return getRetrofit(Constants.BASE_URL).create(CountryApi.class);
    }

    public String generateCountryApiFields(String... fields) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            builder.append(fields[i]);
            if (i != fields.length - 1)
                builder.append(";");
        }
        return builder.toString();
    }

    private Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getHttpClient())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build();
    }

    private OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                //.cache(getCache())
                .addInterceptor(getHttpLoggingInterceptor())
                .addInterceptor(getOfflineCacheInterceptor()).build();
    }

    private Cache getCache() {
        return new Cache(new File(Constants.CACHE_DIR), Constants.CACHE_SIZE);
    }

    private Interceptor getOfflineCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!CountryApplication.isConnected()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(Constants.CACHE_MAX_STALE, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
        return logging;
    }
}
