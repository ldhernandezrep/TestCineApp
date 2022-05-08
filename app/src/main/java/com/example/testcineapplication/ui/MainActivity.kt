package com.example.testcineapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.testcineapplication.R
import com.example.testcineapplication.databinding.ActivityLoginBinding
import com.example.testcineapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.item_movie_row.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Glide.with(this)
            .load("http://static.cinepolis.com/resources/mx/movies/posters/94x137/39326-428836-20220506121740.jpg")
            .centerCrop()
            .into(binding.imvUno)
    }
}