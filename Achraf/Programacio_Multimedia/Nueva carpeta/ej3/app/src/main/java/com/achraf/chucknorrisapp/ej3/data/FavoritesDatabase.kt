package com.achraf.chucknorrisapp.ej3.data

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

// --- Entity ---
/**
 * Entidad Room que representa un chiste guardado como favorito.
 */
@Entity(tableName = "favorite_jokes")
data class FavoriteJoke(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val jokeApiId: String,
    val jokeText: String,
    val category: String? = null
)

// --- DAO ---
/**
 * DAO para los chistes favoritos.
 */
@Dao
interface FavoriteJokeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(joke: FavoriteJoke)

    @Query("SELECT * FROM favorite_jokes ORDER BY id DESC")
    fun getAllFavorites(): Flow<List<FavoriteJoke>>

    @Delete
    suspend fun deleteFavorite(joke: FavoriteJoke)

    @Query("SELECT * FROM favorite_jokes WHERE jokeApiId = :apiId LIMIT 1")
    suspend fun getFavoriteByApiId(apiId: String): FavoriteJoke?
}

// --- Database ---
/**
 * Base de datos Room de la aplicación.
 */
@Database(entities = [FavoriteJoke::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteJokeDao(): FavoriteJokeDao

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
