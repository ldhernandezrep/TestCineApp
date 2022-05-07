package com.example.testcineapplication.data.local

import com.example.testcineapplication.data.local.LoginDAO
import com.example.testcineapplication.data.local.UsuarioEntity

class LocalLoginDataSource(private val loginDAO: LoginDAO) {

    suspend fun saveUsuario(usuarioEntity: UsuarioEntity) = loginDAO.saveUsuario(usuarioEntity)

}