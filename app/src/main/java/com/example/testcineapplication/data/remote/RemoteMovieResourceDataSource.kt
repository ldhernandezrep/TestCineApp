package com.example.testcineapplication.data.remote

import com.example.testcineapplication.cineapiservice.CinepolisApiService
import com.example.testcineapplication.utilities.Constantes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteMovieResourceDataSource(private val cinepolisApiService: CinepolisApiService) {

    suspend fun getMoviesAndResourcesByCine(): MoviesRoute {

        var listMoviesRoute: MoviesRoute

        withContext(Dispatchers.IO) {
            listMoviesRoute = cinepolisApiService.getMoviesAndResourcesByCine(
                Constantes.COUNTRY_CODE,
                61
            )
        }

        return listMoviesRoute
    }

}