package com.example.weatherapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.weatherapp.Repository.CityRepository
import com.example.weatherapp.Sever.ApiClient
import com.example.weatherapp.Sever.ApiService

class CityViewModel(val repository: CityRepository):ViewModel() {
    constructor():this(CityRepository(ApiClient().getClient().create(ApiService::class.java)))

    fun loadCity(q:String,limit:Int)=
        repository.getCityList(q,limit)
}