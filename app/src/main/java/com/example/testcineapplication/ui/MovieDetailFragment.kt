package com.example.testcineapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testcineapplication.R
import com.example.testcineapplication.databinding.FragmentMovieBinding
import com.example.testcineapplication.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    // TODO: Rename and change types of parameters

    private var id: Int? = -1
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            id = bundle.getInt("IdMovie")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
        binding.txvIdMovie.text = id.toString()
    }


}