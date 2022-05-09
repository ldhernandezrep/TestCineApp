package com.example.testcineapplication.repository

import com.example.testcineapplication.data.remote.Movie
import com.example.testcineapplication.data.remote.Routes

interface RoutesRepository {

    suspend fun getRoutes(): List<Routes>


}