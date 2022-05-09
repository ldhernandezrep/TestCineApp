package com.example.testcineapplication.data.remote

import com.example.testcineapplication.data.local.*


data class Movie(
    val rating: String = "",
    val genre: String = "",
    val synopsis: String = "",
    val length: String = "",
    val release_date: String = "",
    val distributor: String = "",
    val media: List<Media> = listOf(),
    val id: Int = -1,
    val name: String = "",
    val code: String = "",
    val original_name: String = ""
)

