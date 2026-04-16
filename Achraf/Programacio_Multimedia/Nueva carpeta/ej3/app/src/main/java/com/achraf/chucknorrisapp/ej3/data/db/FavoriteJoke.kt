package com.achraf.chucknorrisapp.ej3.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// Tabla para guardar los chistes favoritos en la base de datos local (Room)
@Entity(tableName = "favorite_jokes")
data class FavoriteJoke(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val jokeApiId: String, // El ID que nos da la API para no guardar repetidos
    val jokeText: String,  // El texto del chiste
    val category: String? = null // La categoría, si la tiene
)
