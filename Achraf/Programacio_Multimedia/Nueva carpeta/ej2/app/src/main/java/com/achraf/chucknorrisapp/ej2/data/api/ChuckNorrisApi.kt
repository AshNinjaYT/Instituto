package com.achraf.chucknorrisapp.ej2.data.api

import com.achraf.chucknorrisapp.ej2.data.model.ChuckNorrisJoke
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {
    @GET("jokes/random")
    suspend fun getRandomJoke(): ChuckNorrisJoke

    @GET("jokes/categories")
    suspend fun getCategories(): List<String>

    @GET("jokes/random")
    suspend fun getRandomJokeByCategory(
        @Query("category") category: String
    ): ChuckNorrisJoke
}
