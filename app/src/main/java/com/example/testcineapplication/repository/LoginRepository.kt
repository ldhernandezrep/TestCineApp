package com.example.testcineapplication.repository

import com.example.testcineapplication.data.remote.Usuario

interface LoginRepository {

    suspend fun signIn(email: String, passwordUsuario: String): Long

}