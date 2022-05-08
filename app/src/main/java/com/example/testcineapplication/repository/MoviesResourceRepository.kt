package com.example.testcineapplication.repository

import com.example.testcineapplication.data.remote.MoviesRoute

interface MoviesResourceRepository {

    suspend fun getMoviesAndResourcesByCine(): MoviesRoute

}