package com.exotic.kotlinassignment.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val BASE_URL = "https://newsapi.org/v2/"
    private val api:NewsApi

    init {

        val gson = GsonBuilder()
            .setLenient()
            .create()


        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(NewsApi::class.java)
    }

    fun getApiInstance(): NewsApi
    {
        return  api
    }



}