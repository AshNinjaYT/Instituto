package com.achraf.chucknorrisapp.ej3.data

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// --- Model ---
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

// --- API Interface ---
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

// --- Retrofit Client ---
object RetrofitClient {
    private const val BASE_URL = "https://api.chucknorris.io/"

    val api: ChuckNorrisApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChuckNorrisApi::class.java)
    }
}
