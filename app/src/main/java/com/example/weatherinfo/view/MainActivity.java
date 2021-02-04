package com.example.weatherinfo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherinfo.R;
import com.example.weatherinfo.databinding.ActivityMainBinding;
import com.example.weatherinfo.model.entity.ApiError;
import com.example.weatherinfo.model.entity.Main;
import com.example.weatherinfo.model.entity.Result;
import com.example.weatherinfo.model.entity.WeatherInfo;
import com.example.weatherinfo.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Observable;

public class MainActivity extends AppCompatActivity implements Observer<WeatherInfo> {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainViewModel.class);
        mainViewModel.getWeatherInfo().observe(this, this);
        if (savedInstanceState == null) {
            mainViewModel.requestWeatherInfo();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChanged(WeatherInfo weatherInfo) {
        if (weatherInfo != null) {
            binding.setWeatherInfo(weatherInfo);
        } else {
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
