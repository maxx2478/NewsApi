package com.exotic.kotlinassignment.network

import com.exotic.kotlinassignment.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything?q=tesla&from=2021-04-15&sortBy=publishedAt")
    fun getArticles(@Query("apiKey") apikey:String): Call<NewsResponse>

}