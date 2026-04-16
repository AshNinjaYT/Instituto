package com.achraf.chucknorrisapp.ej3.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.achraf.chucknorrisapp.ej3.data.api.RetrofitClient
import com.achraf.chucknorrisapp.ej3.data.db.AppDatabase
import com.achraf.chucknorrisapp.ej3.data.db.FavoriteJoke
import com.achraf.chucknorrisapp.ej3.data.model.ChuckNorrisJoke
import com.achraf.chucknorrisapp.ej3.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// --- ViewModel principal ---
class MainViewModel(private val db: AppDatabase) : ViewModel() {
    
    // Usamos mutableStateOf directamente para simplificarlo
    var randomJoke by mutableStateOf<ChuckNorrisJoke?>(null)
        private set

    var categories by mutableStateOf<List<String>>(emptyList())
        private set

    var isLoadingJoke by mutableStateOf(false)
        private set

    var isLoadingCategories by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var favoriteMessage by mutableStateOf<String?>(null)
        private set

    // Cuando se crea el viewmodel cargamos todo
    init {
        loadRandomJoke()
        loadCategories()
    }

    // Función para pedir un chiste a Retrofit
    fun loadRandomJoke() {
        viewModelScope.launch {
            isLoadingJoke = true
            errorMessage = null
            try {
                randomJoke = RetrofitClient.api.getRandomJoke()
            } catch (e: Exception) {
                // Si falla guardamos el error
                errorMessage = "Error al cargar chiste: ${e.message}"
            } finally {
                isLoadingJoke = false
            }
        }
    }

    // Función para pedir las categorías a Retrofit
    private fun loadCategories() {
        viewModelScope.launch {
            isLoadingCategories = true
            try {
                categories = RetrofitClient.api.getCategories()
            } catch (e: Exception) {
                errorMessage = "Error al cargar categorías: ${e.message}"
            } finally {
                isLoadingCategories = false
            }
        }
    }

    // Función para guardar el chiste actual en favoritos de la BBDD
    fun saveCurrentJokeAsFavorite() {
        val joke = randomJoke ?: return
        viewModelScope.launch {
            val dao = db.favoriteJokeDao()
            // Comprobamos que no esté ya metido en favoritos
            val existing = dao.getFavoriteByApiId(joke.id)
            if (existing != null) {
                favoriteMessage = "Este chiste ya está en favoritos"
            } else {
                val category = joke.categories?.firstOrNull()
                dao.insertFavorite(
                    FavoriteJoke(
                        jokeApiId = joke.id,
                        jokeText = joke.value,
                        category = category
                    )
                )
                favoriteMessage = "¡Chiste guardado en favoritos!"
            }
        }
    }

    // Quitamos el mensaje de la pantalla
    fun clearFavoriteMessage() {
        favoriteMessage = null
    }

    // Factory para inyectar la BBDD al ViewModel
    companion object {
        fun factory(context: Context): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    // Cuidado: getInstance devuelve la bbdd
                    val db = AppDatabase.getInstance(context)
                    return MainViewModel(db) as T
                }
            }
        }
    }
}

// Para traducir las categorías al castellano
fun translateCategory(category: String): String {
    // Usamos un when que es más directo: si es animal -> Animales
    return when (category.lowercase()) {
        "animal" -> "Animales"
        "career" -> "Trabajo"
        "celebrity" -> "Celebridades"
        "dev" -> "Desarrollo"
        "explicit" -> "Explícito"
        "fashion" -> "Moda"
        "food" -> "Comida"
        "history" -> "Historia"
        "money" -> "Dinero"
        "movie" -> "Cine"
        "music" -> "Música"
        "political" -> "Política"
        "religion" -> "Religión"
        "science" -> "Ciencia"
        "sport" -> "Deportes"
        "travel" -> "Viajes"
        // Si no está en la lista la dejamos igual pero en mayúscula
        else -> category.replaceFirstChar { it.uppercase() }
    }
}

// --- Interfaz de la pantalla principal ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, onCategoryClick: (String) -> Unit, onFavoritesClick: () -> Unit) {
    // Con esto borramos el cartel de favoritos después de 2 segundos
    LaunchedEffect(viewModel.favoriteMessage) {
        if (viewModel.favoriteMessage != null) {
            delay(2000)
            viewModel.clearFavoriteMessage()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chistes de Chuck Norris", fontWeight = FontWeight.Bold) },
                // Añadimos el botón de la estrella para ir a favoritos
                actions = {
                    IconButton(onClick = onFavoritesClick) {
                        Icon(Icons.Default.Star, contentDescription = "Favoritos", tint = ColorNaranja)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SuperficieOscura)
            )
        },
        containerColor = FondoOscuro
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            // Toast / Cartel para saber si se ha guardado
            if (viewModel.favoriteMessage != null) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = ColorRosa),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = viewModel.favoriteMessage ?: "",
                        modifier = Modifier.padding(12.dp),
                        color = TextoBlanco,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Text("Chiste Aleatorio", style = MaterialTheme.typography.headlineMedium, color = ColorNaranja)
            Spacer(modifier = Modifier.height(10.dp))
            
            // Carta donde sale el chiste
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SuperficieOscura),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (viewModel.isLoadingJoke) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally), 
                            color = ColorNaranja
                        )
                    } else if (viewModel.randomJoke != null) {
                        Text(
                            text = "\"${viewModel.randomJoke!!.value}\"",
                            style = MaterialTheme.typography.bodyLarge,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 24.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Fila para los dos botones
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Botón Añadir a Favoritos
                            OutlinedButton(
                                onClick = { viewModel.saveCurrentJokeAsFavorite() },
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = ColorRosa),
                                border = androidx.compose.foundation.BorderStroke(1.dp, ColorRosa)
                            ) {
                                Icon(Icons.Default.Favorite, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(Modifier.width(6.dp))
                                Text("Favorito")
                            }

                            // Botón de Siguiente Chiste
                            Button(
                                onClick = { viewModel.loadRandomJoke() },   
                                colors = ButtonDefaults.buttonColors(containerColor = ColorNaranja)
                            ) {
                                Icon(Icons.Default.Refresh, contentDescription = "Nuevo")
                                Spacer(Modifier.width(8.dp))
                                Text("Siguiente")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            Text("Categorías", style = MaterialTheme.typography.headlineMedium, color = ColorVerde)
            Spacer(modifier = Modifier.height(10.dp))
            
            if (viewModel.isLoadingCategories) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally), 
                    color = ColorVerde
                )
            } else {
                // Montamos una grid con 2 columnas para las categorías
                LazyVerticalGrid(
                    modifier = Modifier.weight(1f),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewModel.categories) { category ->
                        CategoryCard(category) { onCategoryClick(category) }
                    }
                }
            }
        }
    }
}

// Widget de la tarjetita de la categoría
@Composable
fun CategoryCard(category: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(SuperficieOscura)
            .clickable { onClick() } // Al hacer click vamos a la ruta que le pasamos
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = translateCategory(category),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = TextoBlanco
        )
    }
}
