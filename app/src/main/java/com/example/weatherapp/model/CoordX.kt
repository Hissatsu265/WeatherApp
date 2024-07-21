package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class CoordX(
    @SerializedName("lat")
    val lat: Double?, // 44.34
    @SerializedName("lon")
    val lon: Double? // 10.99
)