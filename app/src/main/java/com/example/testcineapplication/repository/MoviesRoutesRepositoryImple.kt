package com.example.testcineapplication.repository

import com.example.testcineapplication.core.ConnectionInternet
import com.example.testcineapplication.data.local.UsuarioEntity
import com.example.testcineapplication.data.local.toUsuarioEntity
import com.example.testcineapplication.data.remote.MoviesRoute
import com.example.testcineapplication.data.remote.RemoteLoginDataSource
import com.example.testcineapplication.data.remote.RemoteMovieResourceDataSource

class MoviesRoutesRepositoryImple(private val dataSource: RemoteMovieResourceDataSource) :
    MoviesResourceRepository {

    override suspend fun getMoviesAndResourcesByCine(): MoviesRoute {
        var result: MoviesRoute

        if (ConnectionInternet.isInternetAvailable()) {
            result = dataSource.getMoviesAndResourcesByCine()
        } else
            throw Exception("No hay conexion a internet revise su conexion.")
        return result
    }
}