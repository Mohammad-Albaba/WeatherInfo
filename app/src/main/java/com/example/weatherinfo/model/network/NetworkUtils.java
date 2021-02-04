package com.example.weatherinfo.model.network;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.weatherinfo.R;
import com.example.weatherinfo.model.entity.ApiError;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private final String LOCATION = "q";
    private final String APP_ID = "appid";
    private final String UNITS = "units";

    private static NetworkUtils instance;
    private Context context;
    private final Retrofit retrofit;
    private final WeatherApiInterface weatherApiInterface;

    public static NetworkUtils getInstance(Application application){
        if (instance == null){
            instance = new NetworkUtils(application);
        }
        return instance;
    }

    private NetworkUtils(Application application) {
                 this.context = application.getApplicationContext();
                 retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApiInterface = retrofit.create(WeatherApiInterface.class);
    }

    public WeatherApiInterface getWeatherApiInterface() {
        return weatherApiInterface;
    }

    public HashMap<String, String> getQueryParams(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String cityName = sharedPreferences.getString(context.getString(R.string.city_name_key),context.getString(R.string.city_default_value));
        HashMap<String, String> map = new HashMap<>();
        map.put(LOCATION, cityName);
        map.put(APP_ID, "f5bde83483275210de0ce9945e5077a2");
        map.put(UNITS, "metric");
        return map;
    }
    public ApiError parseHttpError(Response<?> response){
        Converter<ResponseBody, ApiError> converter = retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
        ApiError apiError = null;
        try {
            assert response.errorBody() != null;
            apiError = converter.convert(response.errorBody());
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return apiError;
    }
    public ApiError parseNetworkError(Throwable throwable){
        if (throwable instanceof IOException){
            return new ApiError(true, context.getString(R.string.error_no_internet));
        }else {
            return new ApiError(true, context.getString(R.string.error_general_error));
        }
    }
}