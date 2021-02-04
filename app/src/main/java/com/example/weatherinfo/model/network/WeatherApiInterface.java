package com.example.weatherinfo.model.network;

import com.example.weatherinfo.model.entity.WeatherInfo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface WeatherApiInterface {
    @GET("weather")
    Call<WeatherInfo> getWeatherInfo(@QueryMap HashMap<String, String> queryParams);
}
