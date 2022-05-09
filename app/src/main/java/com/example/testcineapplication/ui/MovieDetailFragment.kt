package com.example.testcineapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcineapplication.R
import com.example.testcineapplication.cineapiservice.CinepolisApiService
import com.example.testcineapplication.core.ResultsState
import com.example.testcineapplication.data.local.DataBaseCine
import com.example.testcineapplication.data.local.LocalMediaDataSource
import com.example.testcineapplication.data.local.LocalMovieDataSource
import com.example.testcineapplication.data.local.LocalRoutesDataSource
import com.example.testcineapplication.data.remote.Movie
import com.example.testcineapplication.data.remote.RemoteMovieResourceDataSource
import com.example.testcineapplication.databinding.FragmentMovieBinding
import com.example.testcineapplication.databinding.FragmentMovieDetailBinding
import com.example.testcineapplication.presentation.MovieDetailViewModel
import com.example.testcineapplication.presentation.MovieViewModel
import com.example.testcineapplication.presentation.MovieViewModelFactory
import com.example.testcineapplication.repository.MoviesRoutesRepositoryImple
import com.example.testcineapplication.ui.login.MovieAdapter


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    // TODO: Rename and change types of parameters

    private var idMovie: Int? = null
    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModelMovies by viewModels<MovieDetailViewModel> {
        MovieViewModelFactory(
            MoviesRoutesRepositoryImple(
                RemoteMovieResourceDataSource(
                    CinepolisApiService.create()
                ),
                LocalMovieDataSource(DataBaseCine.getDatabase(requireContext()).movieDAO()),
                LocalMediaDataSource(DataBaseCine.getDatabase(requireContext()).mediaDAO()),
                LocalRoutesDataSource(DataBaseCine.getDatabase(requireContext()).routeDAO())
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            idMovie = bundle.getInt("IdMvoie")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)


        viewModelMovies.getMoviesRouteById(idMovie!!).observe(viewLifecycleOwner, { x ->
            when (x) {
                is ResultsState.Loading -> {
                    Log.d("Cargando", "Cargando datos")
                }
                is ResultsState.Success -> {
                    var videoView: VideoView = binding.exerciseVideo
                    var stringUrl =
                        x.data.routes.filter { it.code == "trailer_mp4" }[0].sizes.medium +
                                x.data.movies[0].media.filter { it.code == "trailer_mp4" }[0].resource
                    videoView.setVideoPath(stringUrl)
                    videoView.start()

                    binding.txtMovieTitle.text = x.data.movies[0].name
                    binding.txtClasificacion.text = x.data.movies[0].rating
                    binding.txtMovieGenero.text = x.data.movies[0].genre
                    binding.txtMovieDuracion.text = x.data.movies[0].length
                    binding.txtMovieSipnosis.text = x.data.movies[0].synopsis

                }
                is ResultsState.Failure -> {
                    Log.d("Succes", "usuario ${x.exception.message}")
                }
            }

        })

        //var videoView: VideoView = binding.exerciseVideo
        //videoView.setVideoPath(videoUrl)
        //videoView.start()
    }


}