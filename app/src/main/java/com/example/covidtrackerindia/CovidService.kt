package com.example.covidtrackerindia


import retrofit2.Call
import retrofit2.http.GET


interface CovidService {
@GET("us/daily.json")
fun getNationalData(): Call<List<CovidData>>

@GET("stated/daily.json")
fun getStatesData(): Call<List<CovidData>>


}


