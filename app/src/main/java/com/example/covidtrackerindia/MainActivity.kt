package com.example.covidtrackerindia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL ="https://covidtracking.com/api/v1/"
private const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var nationalDailyData: List<CovidData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson =GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val covidService = retrofit.create(CovidService::class.java)

        //Fetch the national data
        covidService.getNationalData().enqueue(object :Callback<List<CovidData>>{
            override fun onFailure(call: Call<List<CovidData>>, t: Throwable) {
                Log.e(TAG,"onFailure $t")
            }

            override fun onResponse(
                call: Call<List<CovidData>>,
                response: Response<List<CovidData>>
            ) {
                Log.i(TAG, "onResponse $response")
                val nationalData = response.body()
                if (nationalData==null) {
                    Log.w(TAG, "Did not receive a valid response body")
                    return
                }
                nationalDailyData = nationalData.reversed()
                Log.i(TAG, "Update graph with national data")
                // TODO: 23/07/21 Update graph with national data 
            }
        })
        // Fetch the state data
    }
}