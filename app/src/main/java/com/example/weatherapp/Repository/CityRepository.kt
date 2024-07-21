package com.example.weatherapp.Repository

import com.example.weatherapp.Sever.ApiService

class CityRepository (val api:ApiService){
    fun getCityList(q:String,limit:Int)=
        api.getCityList(q,limit,"b76f27dfacec9267a3a2b95a68da471f")

}