package com.mertkilic.countries.util;

/**
 * Created by Mert Kilic on 28.2.2017.
 */

public class Constants {
    public static String CACHE_DIR;
    public static final String CACHE_NAME = "http-cache";

    public static final String BASE_URL = "https://restcountries.eu/rest/v2/";

    public static final int NETWORK_CONNECTION_TIMEOUT = 30; // 30 sec
    public static final long CACHE_SIZE = 5 * 1024 * 1024; // 5 MB
    public static final int CACHE_MAX_STALE = 7; // 7 day
}
