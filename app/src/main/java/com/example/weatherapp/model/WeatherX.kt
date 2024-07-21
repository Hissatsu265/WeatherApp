package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class WeatherX(
    @SerializedName("description")
    val description: String?, // light rain
    @SerializedName("icon")
    val icon: String?, // 10d
    @SerializedName("id")
    val id: Int?, // 500
    @SerializedName("main")
    val main: String? // Rain
)