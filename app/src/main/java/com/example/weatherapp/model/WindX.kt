package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class WindX(
    @SerializedName("deg")
    val deg: Int?, // 349
    @SerializedName("gust")
    val gust: Double?, // 1.18
    @SerializedName("speed")
    val speed: Double? // 0.62
)