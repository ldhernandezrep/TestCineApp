package com.example.testcineapplication.data.remote

import com.google.gson.annotations.SerializedName

data class Usuario(
    val access_token: String = "",
    val token_type: String = "",
    val expires_in: String = "",
    val refresh_token: String = "",
    @SerializedName("as:client_id")
    val client_id: String = "",
    val user_name: String = "",
    val country_code: String = "",
    @SerializedName(".issued")
    val issued: String = "",
    @SerializedName(".expires")
    val expires: String = ""

)
