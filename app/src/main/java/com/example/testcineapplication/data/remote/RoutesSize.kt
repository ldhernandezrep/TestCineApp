package com.example.testcineapplication.data.remote

import com.google.gson.annotations.SerializedName

data class RoutesSize(
    val large: String = "",
    val medium: String = "",
    val small: String = "",
    @SerializedName(
        "x-large"
    )
    val xlarge: String = ""
)
