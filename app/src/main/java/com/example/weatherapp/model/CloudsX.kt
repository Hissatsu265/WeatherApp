package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class CloudsX(
    @SerializedName("all")
    val all: Int? // 100
)