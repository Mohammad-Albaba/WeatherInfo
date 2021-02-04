package com.example.weatherinfo.viewmodel;

import android.app.Application;
import android.app.WallpaperInfo;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weatherinfo.model.WeatherRepository;
import com.example.weatherinfo.model.entity.Result;
import com.example.weatherinfo.model.entity.WeatherInfo;


public class MainViewModel extends AndroidViewModel {

    private WeatherRepository weatherRepository;
    private LiveData<WeatherInfo> weatherInfoLiveData;

    public MainViewModel(Application application){
        super(application);
        weatherRepository = WeatherRepository.getInstance(application);
        weatherInfoLiveData = weatherRepository.getWeatherInfo();
    }

    public LiveData<WeatherInfo> getWeatherInfo(){
        return weatherInfoLiveData;
    }

    public void requestWeatherInfo(){
        weatherRepository.requestWeatherInfo();
    }
}