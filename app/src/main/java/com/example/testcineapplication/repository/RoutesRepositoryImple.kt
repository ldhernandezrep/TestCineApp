package com.example.testcineapplication.repository

import com.example.testcineapplication.data.local.LocalLoginDataSource
import com.example.testcineapplication.data.local.LocalRoutesDataSource
import com.example.testcineapplication.data.local.RoutesEntity
import com.example.testcineapplication.data.remote.Media
import com.example.testcineapplication.data.remote.Routes
import com.example.testcineapplication.data.remote.RoutesSize

class RoutesRepositoryImple(private val localDataSource: LocalRoutesDataSource) : RoutesRepository {

    suspend fun getRouteVideo(): String {
        return localDataSource.getRoutesMovies().filter { x -> x.code == "trailer_mp4" }[0].medium
    }

    suspend fun getRouteImagePoster(): String {
        return localDataSource.getRoutesMovies().filter { x -> x.code == "poster" }[0].medium
    }

    suspend fun saveRoutes(routesEntity: RoutesEntity): Long =
        localDataSource.saveRoutes(routesEntity)

    override suspend fun getRoutes(): List<Routes> {
        var listRoute = mutableListOf<Routes>()

        localDataSource.getRoutesMovies().forEach(
            {
                var routesSize: RoutesSize = RoutesSize(it.large, it.medium, it.small, it.xlarge)
                var routes: Routes = Routes(it.code, routesSize)
                listRoute.add(routes)
            }
        )

        return listRoute
    }
}