package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("coord")
    val coord: CoordX?,
    @SerializedName("country")
    val country: String?, // IT
    @SerializedName("id")
    val id: Int?, // 3163858
    @SerializedName("name")
    val name: String?, // Zocca
    @SerializedName("population")
    val population: Int?, // 4593
    @SerializedName("sunrise")
    val sunrise: Int?, // 1661834187
    @SerializedName("sunset")
    val sunset: Int?, // 1661882248
    @SerializedName("timezone")
    val timezone: Int? // 7200
)