package com.achraf.chucknorrisapp.ej3.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// Definimos las consultas a la base de datos
@Dao
interface FavoriteJokeDao {
    // Insertamos un chiste. Si ya existe, lo ignoramos.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(joke: FavoriteJoke)

    // Obtenemos todos los chistes ordenados del más nuevo al más viejo
    @Query("SELECT * FROM favorite_jokes ORDER BY id DESC")
    fun getAllFavorites(): Flow<List<FavoriteJoke>>

    // Borramos un chiste
    @Delete
    suspend fun deleteFavorite(joke: FavoriteJoke)

    // Buscamos un chiste por su ID de la API para saber si ya lo tenemos
    @Query("SELECT * FROM favorite_jokes WHERE jokeApiId = :apiId LIMIT 1")
    suspend fun getFavoriteByApiId(apiId: String): FavoriteJoke?
}
