package com.example.weatherinfo.model.database;

import androidx.room.TypeConverter;

import com.example.weatherinfo.model.entity.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class WeatherListConverter {

    @TypeConverter
    public String fromList(List<Weather> list){
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static List<Weather> fromString(String value){
        Type listType = new TypeToken<List<Weather>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
}