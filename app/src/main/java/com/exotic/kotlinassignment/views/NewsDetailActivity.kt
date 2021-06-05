package com.exotic.kotlinassignment.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.exotic.kotlinassignment.R

class NewsDetailActivity : Fragment() {

    var webview:WebView?=null
    var root:View?=null
    var url:String?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.activity_news_detail, container, false)
        initializeViews(root!!)
        return root
    }

    override fun onStart() {
        super.onStart()
        webview!!.loadUrl(url!!)
    }

    private fun initializeViews(root: View) {
        webview = root.findViewById(R.id.webview)
        url = requireArguments().getString("url")

    }

}