package com.exotic.kotlinassignment.views

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exotic.kotlinassignment.model.Article
import com.exotic.kotlinassignment.model.NewsResponse
import com.exotic.kotlinassignment.network.ApiService
import com.exotic.kotlinassignment.utils.KeyConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivityViewModel : ViewModel()
{
    val articles = MutableLiveData<ArrayList<Article>>()
    val error = MutableLiveData<Boolean>()
    val apiService = ApiService()

    fun loadArticles()
    {
        val c: Date = Calendar.getInstance().getTime()
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate: String = df.format(c)

        val request = apiService.getApiInstance()
        val call = request.getArticles(formattedDate,"publishedAt" ,KeyConstants.MY_KEY)
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {

                if (response.isSuccessful) {
                    error.value = false
                    articles.value = response.body()!!.articles
                } else {
                    Log.i("errorcode", response.code().toString())
                    error.value = true
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                error.value = true
            }
        })
    }

}