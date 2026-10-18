package com.achraf.chucknorrisapp.ej3.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Cliente de Retrofit para conectarnos a internet
object RetrofitClient {
    private const val BASE_URL = "https://api.chucknorris.io/"

    val api: ChuckNorrisApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Usamos Gson para pasar el JSON a objetos de Kotlin
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChuckNorrisApi::class.java)
    }
}
