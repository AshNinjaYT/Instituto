package com.achraf.chucknorrisapp.ej3.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Clase principal de la base de datos de Room
@Database(entities = [FavoriteJoke::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    
    // Conectamos con el DAO
    abstract fun favoriteJokeDao(): FavoriteJokeDao

    // Usamos un companion object (patrón Singleton) para no abrir varias veces la BD
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "chuck_norris_favorites.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
