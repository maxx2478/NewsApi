package com.exotic.kotlinassignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exotic.kotlinassignment.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        val actionbar = supportActionBar
        actionbar!!.hide()

    }
}