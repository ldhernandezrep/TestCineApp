package com.example.testcineapplication.repository

import com.example.testcineapplication.core.ConnectionInternet
import com.example.testcineapplication.data.local.LocalLoginDataSource
import com.example.testcineapplication.data.local.UsuarioEntity
import com.example.testcineapplication.data.local.toUsuarioEntity
import com.example.testcineapplication.data.remote.RemoteLoginDataSource
import com.example.testcineapplication.data.remote.Usuario

class LoginRepositoryImple(
    private val dataSource: RemoteLoginDataSource,
    private val localDataSource: LocalLoginDataSource
) : LoginRepository {

    /***
     * Metodo para iniciar sesion consulta en el web service por usuario y contraseña
     */
    override suspend fun signIn(
        email: String,
        passwordUsuario: String
    ): Long {

        var result: Long = -1

        if (ConnectionInternet.isInternetAvailable()) {
            var usuarioEntity: UsuarioEntity =
                dataSource.signIn(email, passwordUsuario).toUsuarioEntity()
            result = localDataSource.saveUsuario(usuarioEntity)
        } else
            throw Exception("No hay conexion a internet revise su conexion.")
        return result
    }


}