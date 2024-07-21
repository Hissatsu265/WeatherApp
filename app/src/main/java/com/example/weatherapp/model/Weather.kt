package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("description")
    val description: String?, // moderate rain
    @SerializedName("icon")
    val icon: String?, // 10d
    @SerializedName("id")
    val id: Int?, // 501
    @SerializedName("main")
    val main: String? // Rain
)