package com.achraf.chucknorrisapp.ej2.data.model

import com.google.gson.annotations.SerializedName

data class ChuckNorrisJoke(
    @SerializedName("id")
    val id: String,

    @SerializedName("value")
    val value: String,

    @SerializedName("categories")
    val categories: List<String>? = null,

    @SerializedName("icon_url")
    val iconUrl: String,

    @SerializedName("url")
    val url: String
)
