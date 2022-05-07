package com.example.testcineapplication.cineapiservice

import com.example.testcineapplication.data.remote.Usuario
import com.example.testcineapplication.utilities.Constantes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface CinepolisApiService {
    @Headers("api_key: stage_HNYh3RaK_Test")
    @FormUrlEncoded
    @POST("v2/oauth/token")
    suspend fun signIn(
        @Field("country_code") country_code: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") gran_type: String,
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String
    ): Usuario


    companion object {

        fun create(): CinepolisApiService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder().connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CinepolisApiService::class.java)
        }

    }

}