package com.example.testcineapplication.data.local

class LocalMovieDataSource(private val movieDAO: MovieDAO) {

    suspend fun saveMovie(movieEntity: MovieEntity): Long = movieDAO.saveMovie(movieEntity)
    suspend fun getMovies(): List<MovieEntity> = movieDAO.getMovies()
    suspend fun getMoviesBYId(idMovie:Int): List<MovieEntity> = movieDAO.getMoviesBYId(idMovie)

}