package com.example.weatherinfo.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weatherinfo.model.entity.WeatherInfo;

@Dao
public interface WeatherInfoDao {

    @Query("SELECT * From weather_info LIMIT 1")
    LiveData<WeatherInfo> getWeatherInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertWeatherInfo(WeatherInfo weatherInfo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateWeatherInfo(WeatherInfo weatherInfo);

    @Delete
    int deleteWeather(WeatherInfo weatherInfo);
    @Query("DELETE FROM weather_info")
    void emptyTable();
}