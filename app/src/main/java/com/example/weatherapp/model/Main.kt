package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double?, // 298.74
    @SerializedName("grnd_level")
    val grndLevel: Int?, // 933
    @SerializedName("humidity")
    val humidity: Int?, // 64
    @SerializedName("pressure")
    val pressure: Int?, // 1015
    @SerializedName("sea_level")
    val seaLevel: Int?, // 1015
    @SerializedName("temp")
    val temp: Double?, // 298.48
    @SerializedName("temp_max")
    val tempMax: Double?, // 300.05
    @SerializedName("temp_min")
    val tempMin: Double? // 297.56
)