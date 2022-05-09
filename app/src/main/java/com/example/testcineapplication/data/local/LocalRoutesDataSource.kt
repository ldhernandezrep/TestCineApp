package com.example.testcineapplication.data.local

import com.example.testcineapplication.data.remote.Routes

class LocalRoutesDataSource(private val routesDAO: RoutesDAO) {

    suspend fun getRoutesMovies(): List<RoutesEntity> = routesDAO.getRoutesMovies()

    suspend fun saveRoutes(routesEntity: RoutesEntity): Long = routesDAO.saveRoutes(routesEntity)

}