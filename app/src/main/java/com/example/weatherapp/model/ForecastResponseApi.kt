package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class ForecastResponseApi(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Int?, // 40
    @SerializedName("cod")
    val cod: String?, // 200
    @SerializedName("list")
    val list: List<data1>?,
    @SerializedName("message")
    val message: Int? // 0
){

data class data1 (
    @SerializedName("clouds")
    val clouds: CloudsX?,
    @SerializedName("dt")
    val dt: Int?, // 1661871600
    @SerializedName("dt_txt")
    val dtTxt: String?, // 2022-08-30 15:00:00
    @SerializedName("main")
    val main: MainX?,
    @SerializedName("pop")
    val pop: Double?, // 0.32
    @SerializedName("rain")
    val rain: RainX?,
    @SerializedName("sys")
    val sys: SysX?,
    @SerializedName("visibility")
    val visibility: Int?, // 10000
    @SerializedName("weather")
    val weather: List<WeatherX>?,
    @SerializedName("wind")
    val wind: WindX?
)
}