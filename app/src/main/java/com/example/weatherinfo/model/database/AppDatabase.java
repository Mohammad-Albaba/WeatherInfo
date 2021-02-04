package com.example.weatherinfo.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weatherinfo.model.entity.WeatherInfo;

@Database( entities = {WeatherInfo.class}, version = 1, exportSchema = false)
@TypeConverters({WeatherListConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    private static final String DATABASE_NAME = "weather_db";

    public static AppDatabase getInstance(Context context){
        if (instance == null){
          instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
        }
        return instance;
    }
    public abstract WeatherInfoDao weatherInfoDao();
}