package com.example.testcineapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcineapplication.R
import com.example.testcineapplication.cineapiservice.CinepolisApiService
import com.example.testcineapplication.core.ResultsState
import com.example.testcineapplication.data.local.*
import com.example.testcineapplication.data.remote.*
import com.example.testcineapplication.databinding.FragmentMovieBinding
import com.example.testcineapplication.presentation.LoginViewModel
import com.example.testcineapplication.presentation.LoginViewModelFactory
import com.example.testcineapplication.presentation.MovieViewModel
import com.example.testcineapplication.presentation.MovieViewModelFactory
import com.example.testcineapplication.repository.LoginRepositoryImple
import com.example.testcineapplication.repository.MoviesRoutesRepositoryImple
import com.example.testcineapplication.repository.RoutesRepositoryImple
import com.example.testcineapplication.ui.login.MovieAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentMovieBinding
    private lateinit var adapterMovie: MovieAdapter

    private val viewModelMovies by viewModels<MovieViewModel> {
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        viewModelMovies.getMoviesAndResourcesByCine().observe(viewLifecycleOwner, { x ->
            when (x) {
                is ResultsState.Loading -> {
                    Log.d("Cargando", "Cargando datos")
                }
                is ResultsState.Success -> {

                    var gridLayoutManager: GridLayoutManager = GridLayoutManager(
                        requireContext(), // context
                        3, // span count
                        RecyclerView.VERTICAL, // orientation
                        false // reverse layout
                    )
                    binding.rcvCartelera.layoutManager = gridLayoutManager
                    adapterMovie = MovieAdapter(
                        x.data.movies, this, x.data.routes
                    )
                    binding.rcvCartelera.adapter = adapterMovie
                    Log.d("Succes", "usuario ${x.data}")
                }
                is ResultsState.Failure -> {
                    Log.d("Succes", "usuario ${x.exception.message}")
                }
            }

        })

    }

    override fun onMovieClick(movie: Movie, route: List<Routes>) {
        findNavController().navigate(
            R.id.action_movieFragment_to_movieDetailFragment,
            bundleOf(
                "IdMvoie" to movie.id
            )

        )
    }

}