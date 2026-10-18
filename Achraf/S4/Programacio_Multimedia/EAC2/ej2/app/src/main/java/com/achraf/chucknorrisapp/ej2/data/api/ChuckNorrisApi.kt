package com.achraf.chucknorrisapp.ej2.data.api

import com.achraf.chucknorrisapp.ej2.data.model.ChuckNorrisJoke
import retrofit2.http.GET
import retrofit2.http.Query

// Interfaz para llamar a la API de Chuck Norris
interface ChuckNorrisApi {
    
    // Pillar un chiste aleatorio
    @GET("jokes/random")
    suspend fun getRandomJoke(): ChuckNorrisJoke

    // Coger la lista de las categorías
    @GET("jokes/categories")
    suspend fun getCategories(): List<String>

    // Pillar un chiste según la categoría que le pasemos
    @GET("jokes/random")
    suspend fun getRandomJokeByCategory(
        @Query("category") category: String
    ): ChuckNorrisJoke
}
