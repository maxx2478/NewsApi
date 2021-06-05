package com.exotic.kotlinassignment.views

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exotic.kotlinassignment.R
import com.exotic.kotlinassignment.adapter.NewsAdapter
import com.exotic.kotlinassignment.model.Article
import com.exotic.kotlinassignment.utils.SessionManager
import com.google.android.material.snackbar.Snackbar

class MainActivity : Fragment()
{
    var recyclerView:RecyclerView?=null
    lateinit var newsAdapter:NewsAdapter
    var progressBar:ProgressBar?=null
    lateinit var mainViewModel: MainActivityViewModel
    var list:ArrayList<Article>?=null
    var errortext:TextView?=null
    var root:View?=null
    var refresh:ImageButton?=null
    var articleSize:Int?=null
    private var sessionManager: SharedPreferences?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.activity_main, container, false)
        initiliazeViews(root!!)
        onClickListeners()
        list = arrayListOf()
        newsAdapter = NewsAdapter(requireActivity(), list!!)
        mainViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        return root

    }


    override fun onStart() {
        super.onStart()
        //fetching data from Viewmodel
        mainViewModel.loadArticles()
        recyclerView!!.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView!!.adapter = newsAdapter
        ObserveViewModel()

    }

    private fun initiliazeViews(root: View) {

        recyclerView = root.findViewById(R.id.recyclerview)
        recyclerView!!.visibility = View.GONE
        progressBar = root.findViewById(R.id.progressbar)
        errortext = root.findViewById(R.id.errorview)
        refresh = root.findViewById(R.id.refreshData)
        sessionManager = SessionManager.customPrefs(requireContext(), "session")


    }

    private fun onClickListeners()
    {
        refresh!!.setOnClickListener {
            mainViewModel.loadArticles()
            if (sessionManager!!.getInt("articleSize", 0)==articleSize)
            {
                Toast.makeText(requireContext(), "No new articles found !", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(requireContext(), "Articles refreshed !", Toast.LENGTH_SHORT).show()
            }

        }
    }

    //Onbserve viewmodel livedata variables
    private fun ObserveViewModel()
    {

        //Observe Articles
        mainViewModel.articles.observe(requireActivity(), {
            if (it.isNotEmpty())
            {
                sessionManager!!.edit().putInt("articleSize", it.size).apply()
                articleSize = it.size
                recyclerView!!.visibility = View.VISIBLE
                progressBar!!.visibility = View.GONE
                newsAdapter.updateData(it!!)
            }
        })

        //Observe error variable
        mainViewModel.error.observe(requireActivity(), {
            if (it)
            {
                recyclerView!!.visibility = View.GONE
                progressBar!!.visibility = View.GONE
                errortext!!.visibility = View.VISIBLE
            }
        })

    }

}
