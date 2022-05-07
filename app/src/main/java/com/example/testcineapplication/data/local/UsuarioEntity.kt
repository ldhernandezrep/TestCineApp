package com.example.testcineapplication.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testcineapplication.data.remote.Usuario
import com.google.gson.annotations.SerializedName

@Entity
class UsuarioEntity(
    @PrimaryKey
    val access_token: String = "",

    @ColumnInfo(name = "token_type")
    val token_type: String = "",

    @ColumnInfo(name = "expires_in")
    val expires_in: String = "",

    @ColumnInfo(name = "refresh_token")
    val refresh_token: String = "",

    @ColumnInfo(name = "client_id")
    val client_id: String = "",

    @ColumnInfo(name = "username")
    val username: String = "",

    @ColumnInfo(name = "country_code")
    val country_code: String = "",

    @ColumnInfo(name = "issued")
    val issued: String = "",

    @ColumnInfo(name = "expires")
    val expires: String = ""
)

fun Usuario.toUsuarioEntity(): UsuarioEntity = UsuarioEntity(
    this.access_token,
    this.token_type,
    this.expires_in,
    this.refresh_token,
    this.client_id,
    this.user_name,
    this.country_code,
    this.issued,
    this.expires
)
