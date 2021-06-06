package com.exotic.kotlinassignment.network

import com.exotic.kotlinassignment.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything?q=tesla")
    fun getArticles(@Query("from") from:String,
                    @Query("sortBy") sortBy:String,
                    @Query("apiKey") apikey:String): Call<NewsResponse>
      //sortBy=publishedAt
}