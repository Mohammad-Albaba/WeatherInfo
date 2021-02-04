package com.example.weatherinfo.model;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherinfo.AppExecutor;
import com.example.weatherinfo.model.database.AppDatabase;
import com.example.weatherinfo.model.entity.Result;
import com.example.weatherinfo.model.entity.WeatherInfo;
import com.example.weatherinfo.model.network.NetworkUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private static WeatherRepository instance ;
    private NetworkUtils networkUtils;
    private AppDatabase appDatabase;
    private LiveData<WeatherInfo> weatherInfoLiveData;

    public static WeatherRepository getInstance(Application application){
        if (instance == null){
            instance = new WeatherRepository(application);
        }
        return instance;
    }
    private WeatherRepository(Application application){
        networkUtils = NetworkUtils.getInstance(application);
        appDatabase = AppDatabase.getInstance(application.getApplicationContext());
        weatherInfoLiveData = appDatabase.weatherInfoDao().getWeatherInfo();

    }

    public void requestWeatherInfo(){
        HashMap<String, String> params = networkUtils.getQueryParams();
        networkUtils.getWeatherApiInterface().getWeatherInfo(params).enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                if (response.isSuccessful()){
                    AppExecutor.getInstance().getDiskIoExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            appDatabase.weatherInfoDao().insertWeatherInfo(response.body());
                        }
                    });

//                    dataWrapper.setValue(Result.success(response.body()));
                }else {
//                    dataWrapper.setValue(Result.error(networkUtils.parseHttpError(response)));

                }
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
//                dataWrapper.setValue(Result.error(networkUtils.parseNetworkError(t)));
            }
        });
    }

    public LiveData<WeatherInfo> getWeatherInfo() {
        return weatherInfoLiveData;
    }
}