package com.example.testcineapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MediaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMedia(mediaEntity: MediaEntity): Long

    @Query("SELECT resource,type,code,idMovie FROM MediaEntity")
    suspend fun getMediaOfMovie(): List<MediaEntity>

    @Query("SELECT resource,type,code,idMovie FROM MediaEntity WHERE idMovie = :idMovie")
    suspend fun getMediaOfMovieById(idMovie: Int): List<MediaEntity>

}