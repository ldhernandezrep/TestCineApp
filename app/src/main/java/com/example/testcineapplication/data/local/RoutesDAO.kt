package com.example.testcineapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoutesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRoutes(routesEntity: RoutesEntity): Long

    @Query("SELECT code,large,medium,small,xlarge FROM RoutesEntity")
    suspend fun getRoutesMovies(): List<RoutesEntity>

}