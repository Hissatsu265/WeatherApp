package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class CurrentResponseApi(
    @SerializedName("base")
    val base: String?, // stations
    @SerializedName("clouds")
    val clouds: Clouds?,
    @SerializedName("cod")
    val cod: Int?, // 200
    @SerializedName("coord")
    val coord: Coord?,
    @SerializedName("dt")
    val dt: Int?, // 1661870592
    @SerializedName("id")
    val id: Int?, // 3163858
    @SerializedName("main")
    val main: Main?,
    @SerializedName("name")
    val name: String?, // Zocca
    @SerializedName("rain")
    val rain: Rain?,
    @SerializedName("sys")
    val sys: Sys?,
    @SerializedName("timezone")
    val timezone: Int?, // 7200
    @SerializedName("visibility")
    val visibility: Int?, // 10000
    @SerializedName("weather")
    val weather: List<Weather?>?,
    @SerializedName("wind")
    val wind: Wind?
)