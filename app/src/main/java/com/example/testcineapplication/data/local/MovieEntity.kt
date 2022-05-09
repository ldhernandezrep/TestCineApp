package com.example.testcineapplication.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testcineapplication.data.remote.Media
import com.example.testcineapplication.data.remote.Movie
import com.example.testcineapplication.data.remote.Usuario

@Entity
class MovieEntity(
    @ColumnInfo(name = "rating")
    val rating: String = "",
    @ColumnInfo(name = "genre")
    val genre: String = "",
    @ColumnInfo(name = "synopsis")
    val synopsis: String = "",
    @ColumnInfo(name = "length")
    val length: String = "",
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    @ColumnInfo(name = "distributor")
    val distributor: String = "",
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "code")
    val code: String = "",
    @ColumnInfo(name = "original_name")
    val original_name: String = ""
)

fun Movie.toMovieEntity(): MovieEntity = MovieEntity(
    this.rating,
    this.genre,
    this.synopsis,
    this.length,
    this.release_date,
    this.distributor,
    this.id,
    this.name,
    this.code,
    this.original_name
)

fun MovieEntity.toMovieModel(listMedia: List<Media>): Movie = Movie(
    this.rating,
    this.genre,
    this.synopsis,
    this.length,
    this.release_date,
    this.distributor,
    media = listMedia,
    this.id,
    this.name,
    this.code,
    this.original_name
)


fun MovieEntity.toMovieModelWhitOutMedia(): Movie = Movie(
    this.rating,
    this.genre,
    this.synopsis,
    this.length,
    this.release_date,
    this.distributor,
    media = listOf(),
    this.id,
    this.name,
    this.code,
    this.original_name
)

data class MovieList(val results: List<Movie> = listOf())


fun List<MovieEntity>.toListMovie(listMedia: List<Media>): MovieList {
    val resultList = mutableListOf<Movie>()
    this.forEach {
        resultList.add(it.toMovieModel(listMedia))
    }

    return MovieList(resultList)
}