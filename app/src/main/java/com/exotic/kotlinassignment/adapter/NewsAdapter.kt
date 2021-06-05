package com.exotic.kotlinassignment.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.exotic.kotlinassignment.R
import com.exotic.kotlinassignment.model.Article
import com.exotic.kotlinassignment.model.NewsResponse
import com.exotic.kotlinassignment.views.NewsDetailActivity
import com.squareup.picasso.Picasso

class NewsAdapter : RecyclerView.Adapter<NewsViewHolderx> {

    var context:Context?=null
    var data:ArrayList<Article>?=null

    constructor(context: Context, data:ArrayList<Article>)
    {
        this.context = context
        this.data = data
    }

    fun updateData(data:ArrayList<Article>)
    {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolderx {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_news, parent, false)
        return NewsViewHolderx(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolderx, position: Int) {

        val article = data!!.get(position)
        holder.title.text =  article.title
        holder.desc.text = article.description
        Picasso.get().load(article.urlToImage).into(holder.imageView)
        holder.itemView.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(v: View?) {

                val bundle = Bundle()
                bundle.putString("url", article.url)
                holder.itemView.findNavController().navigate(R.id.action_mainActivity_to_newsDetailActivity, bundle)

            }

        })


    }

    override fun getItemCount(): Int {
         return  data!!.size
    }

}

class NewsViewHolderx(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val imageView:ImageView = itemView.findViewById(R.id.imageView)
    val title:TextView = itemView.findViewById(R.id.newsTitle)
    val desc:TextView = itemView.findViewById(R.id.newsDesc)


}