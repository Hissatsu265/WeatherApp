package com.example.weatherapp.model


import com.google.gson.annotations.SerializedName

data class CityResponseApiItem(
    @SerializedName("country")
    var country: String?, // GB
    @SerializedName("lat")
    var lat: Double?, // 51.5073219
    @SerializedName("local_names")
    var localNames: LocalNames?,
    @SerializedName("lon")
    var lon: Double?, // -0.1276474
    @SerializedName("name")
    var name: String?, // London
    @SerializedName("state")
    var state: String? // England
)