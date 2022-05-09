package com.example.testcineapplication.data.local

class LocalMediaDataSource(private val mediaDAO: MediaDAO) {

    suspend fun saveMedia(mediaEntity: MediaEntity): Long = mediaDAO.saveMedia(mediaEntity)
    suspend fun getMediaOfMovie(): List<MediaEntity> = mediaDAO.getMediaOfMovie()
    suspend fun getMediaOfMovieById(idMoview: Int) = mediaDAO.getMediaOfMovieById(idMoview)

}