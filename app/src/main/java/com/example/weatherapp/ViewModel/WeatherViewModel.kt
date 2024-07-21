package com.example.weatherapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.weatherapp.Repository.WeatherRepository
import com.example.weatherapp.Sever.ApiClient
import com.example.weatherapp.Sever.ApiService

class WeatherViewModel(val repository: WeatherRepository):ViewModel() {
    constructor():this(WeatherRepository(ApiClient().getClient().create(ApiService::class.java)))
    fun loadCurrentWeather(lat:Double,lon:Double,unit:String)=
        repository.getCurrentWeather(lat,lon,unit)

    fun loadForecastWeather(lat:Double,lon:Double,unit:String)=
        repository.getForecastWeather(lat,lon,unit)
}
