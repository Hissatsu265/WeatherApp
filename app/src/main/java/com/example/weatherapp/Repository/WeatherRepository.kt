package com.example.weatherapp.Repository

import com.example.weatherapp.Sever.ApiService

class WeatherRepository(val api:ApiService) {
    fun getCurrentWeather(lat:Double,lon:Double,unit:String)=
        api.getCurrentWeather(lat,lon,unit,"b76f27dfacec9267a3a2b95a68da471f")

    fun getForecastWeather(lat:Double,lon:Double,unit:String)=
        api.getForecasttWeather(lat,lon,unit,"b76f27dfacec9267a3a2b95a68da471f")
}