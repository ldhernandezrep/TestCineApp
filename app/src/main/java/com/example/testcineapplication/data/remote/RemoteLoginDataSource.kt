package com.example.testcineapplication.data.remote

import com.example.testcineapplication.cineapiservice.CinepolisApiService
import com.example.testcineapplication.utilities.Constantes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteLoginDataSource(private val cinepolisApiService: CinepolisApiService) {

    suspend fun signIn(
        email: String,
        password: String
    ): Usuario {
        var usuario: Usuario
        withContext(Dispatchers.IO) {
            usuario =
                cinepolisApiService.signIn(
                    Constantes.COUNTRY_CODE,
                    email,
                    password,
                    Constantes.GRANT_TYPE,
                    Constantes.CLIENT_ID,
                    Constantes.CLIENT_SECRET
                )
        }
        return usuario;
    }

}