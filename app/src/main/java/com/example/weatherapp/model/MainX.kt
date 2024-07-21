package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class MainX(
    @SerializedName("feels_like")
    val feelsLike: Double?, // 296.98
    @SerializedName("grnd_level")
    val grndLevel: Int?, // 933
    @SerializedName("humidity")
    val humidity: Int?, // 69
    @SerializedName("pressure")
    val pressure: Int?, // 1015
    @SerializedName("sea_level")
    val seaLevel: Int?, // 1015
    @SerializedName("temp")
    val temp: Double?, // 296.76
    @SerializedName("temp_kf")
    val tempKf: Double?, // -1.11
    @SerializedName("temp_max")
    val tempMax: Double?, // 297.87
    @SerializedName("temp_min")
    val tempMin: Double? // 296.76
)