package com.example.testcineapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUsuario(usuarioEntity: UsuarioEntity): Long


}