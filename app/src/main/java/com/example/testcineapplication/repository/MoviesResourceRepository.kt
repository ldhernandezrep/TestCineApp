package com.example.testcineapplication.repository

import com.example.testcineapplication.data.remote.Movie
import com.example.testcineapplication.data.remote.MoviesRoute

interface MoviesResourceRepository {

    suspend fun getMovies(): MoviesRoute
    suspend fun getMoviesBYId(idMovie: Int): MoviesRoute

}