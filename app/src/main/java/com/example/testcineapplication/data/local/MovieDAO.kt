package com.example.testcineapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movieEntity: MovieEntity): Long

    @Query("SELECT code,distributor,distributor,id,length,name,original_name,genre,rating,release_date,synopsis FROM MovieEntity")
    suspend fun getMovies(): List<MovieEntity>;

    @Query("SELECT code,distributor,distributor,id,length,name,original_name,genre,rating,release_date,synopsis FROM MovieEntity WHERE id = :idMovie")
    suspend fun getMoviesBYId(idMovie: Int): List<MovieEntity>


}