package com.example.testcineapplication.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testcineapplication.data.remote.Media
import com.example.testcineapplication.data.remote.Routes
import com.example.testcineapplication.data.remote.RoutesSize
import com.google.gson.annotations.SerializedName

@Entity
class RoutesEntity(
    @PrimaryKey
    val code: String = "",
    @ColumnInfo(name = "large")
    val large: String = "",
    @ColumnInfo(name = "medium")
    val medium: String = "",
    @ColumnInfo(name = "small")
    val small: String = "",
    @ColumnInfo(name = "xlarge")
    val xlarge: String = ""
)

data class RoutesList(val results: List<Routes> = listOf())



