package com.example.testcineapplication.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testcineapplication.data.remote.Media
import com.example.testcineapplication.data.remote.Movie
import com.example.testcineapplication.data.remote.Usuario

@Entity(primaryKeys = ["idMovie","code"])
class MediaEntity(
    @ColumnInfo(name = "resource") val resource: String = "",
    @ColumnInfo(name = "type") val type: String = "",
    @ColumnInfo(name = "code") val code: String = "",
    @ColumnInfo(name = "idMovie")
    val idMovie: Int = -1
)

data class MediaList(val results: List<Media> = listOf())

fun Media.toMediaEntity(idMovie: Int): MediaEntity = MediaEntity(
    this.resource,
    this.type,
    this.code,
    idMovie = idMovie
)

fun MediaEntity.toMediaModel(): Media = Media(
    this.resource,
    this.type,
    this.code,
    this.idMovie
)

fun List<MediaEntity>.toListMediaMode(): MediaList {
    val resultList = mutableListOf<Media>()
    this.forEach {
        resultList.add(it.toMediaModel())
    }

    return MediaList(resultList)
}

