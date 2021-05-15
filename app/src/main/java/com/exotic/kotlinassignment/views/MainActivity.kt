package com.exotic.kotlinassignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exotic.kotlinassignment.R
import com.exotic.kotlinassignment.adapter.NewsAdapter
import com.exotic.kotlinassignment.model.Article

class MainActivity : AppCompatActivity()
{
    var recyclerView:RecyclerView?=null
    lateinit var newsAdapter:NewsAdapter
    var progressBar:ProgressBar?=null
    lateinit var mainViewModel: MainActivityViewModel
    var list:ArrayList<Article>?=null
    var errortext:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initiliazeViews()

        val actionBar = supportActionBar
        actionBar!!.title = "News Api"

        newsAdapter = NewsAdapter(this, list!!)
        mainViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mainViewModel.loadArticles()
        recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView!!.adapter = newsAdapter

        ObserveViewModel()

    }

    private fun initiliazeViews() {
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView!!.visibility = View.GONE
        progressBar = findViewById(R.id.progressbar)
        errortext = findViewById(R.id.errorview)
        list = arrayListOf()

    }

    private fun ObserveViewModel()
    {
        mainViewModel.articles.observe(this, {
            if (it.isNotEmpty())
            {
                recyclerView!!.visibility = View.VISIBLE
                progressBar!!.visibility = View.GONE
                newsAdapter.updateData(it!!)
            }
        })

        mainViewModel.error.observe(this, {

            if (it)
            {
                recyclerView!!.visibility = View.GONE
                progressBar!!.visibility = View.GONE
                errortext!!.visibility = View.VISIBLE

            }

        })

    }

}
