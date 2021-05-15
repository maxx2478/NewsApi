package com.exotic.kotlinassignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.exotic.kotlinassignment.R

class NewsDetailActivity : AppCompatActivity() {

    var webview:WebView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val actionBar = supportActionBar
        actionBar!!.title = "News Details"

        initializeViews()
        val string = intent.getStringExtra("url")
        webview!!.loadUrl(string!!)

    }

    private fun initializeViews() {
     webview = findViewById(R.id.webview)
    }

}