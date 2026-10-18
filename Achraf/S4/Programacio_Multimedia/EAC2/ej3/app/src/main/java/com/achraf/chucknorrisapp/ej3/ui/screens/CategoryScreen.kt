package com.achraf.chucknorrisapp.ej3.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

// --- ViewModel para la pantalla de Categorías ---
class CategoryViewModel(private val db: AppDatabase) : ViewModel() {
    
    // Convertimos a mutableStateOf para que sea mas facil
    var joke by mutableStateOf<ChuckNorrisJoke?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var favoriteMessage by mutableStateOf<String?>(null)
        private set

    // Método que llamamos para traer un chiste de una categoría 
    fun loadJokeForCategory(category: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                // Buscamos el chiste en la api
                joke = RetrofitClient.api.getRandomJokeByCategory(category)
            } catch (e: Exception) {
                // Si hay error lo guardamos para que no pete la app
                errorMessage = "Error al cargar chiste de categoría: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    // Guarda el chiste actual como favorito
    fun saveCurrentJokeAsFavorite(category: String) {
        val currentJoke = joke ?: return // Si es nulo no hacemos nada
        viewModelScope.launch {
            val dao = db.favoriteJokeDao()
            val existing = dao.getFavoriteByApiId(currentJoke.id)
            if (existing != null) {
                favoriteMessage = "Este chiste ya está en favoritos"
            } else {
                dao.insertFavorite(
                    FavoriteJoke(
                        jokeApiId = currentJoke.id,
                        jokeText = currentJoke.value,
                        category = category
                    )
                )
                favoriteMessage = "¡Chiste guardado en favoritos!"
            }
        }
    }

    // Para borrar el toast
    fun clearFavoriteMessage() {
        favoriteMessage = null
    }

    // Necesario para pasarle la base de datos al viewmodel
    companion object {
        fun factory(context: Context): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val db = AppDatabase.getInstance(context)
                    return CategoryViewModel(db) as T
                }
            }
        }
    }
}

// --- Pantalla UI ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    category: String, 
    viewModel: CategoryViewModel, 
    onBackClick: () -> Unit
) {
    // Solo cargamos la primera vez que entra a la categoría
    LaunchedEffect(category) {
        viewModel.loadJokeForCategory(category)
    }

    // Borramos el cartel de guardar
    LaunchedEffect(viewModel.favoriteMessage) {
        if (viewModel.favoriteMessage != null) {
            delay(2000)
            viewModel.clearFavoriteMessage()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(translateCategory(category), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        // Botón de atrás (Volver a la portada)
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SuperficieOscura)
            )
        },
        containerColor = FondoOscuro
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (viewModel.favoriteMessage != null) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
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

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SuperficieOscura),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(color = ColorVerde)
                    } else if (viewModel.joke != null) {
                        Text(
                            text = "\"${viewModel.joke!!.value}\"",
                            style = MaterialTheme.typography.bodyLarge,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 28.sp,
                            color = TextoBlanco
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Botón Añadir a Favoritos
                            OutlinedButton(
                                onClick = { viewModel.saveCurrentJokeAsFavorite(category) },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = ColorRosa),
                                border = androidx.compose.foundation.BorderStroke(1.dp, ColorRosa)
                            ) {
                                Icon(Icons.Default.Favorite, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(Modifier.width(8.dp))
                                Text("Favorito")
                            }

                            // Botón Siguiente
                            Button(
                                onClick = { viewModel.loadJokeForCategory(category) },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = ColorVerde)
                            ) {
                                Icon(Icons.Default.Refresh, contentDescription = "Otro")
                                Spacer(Modifier.width(8.dp))
                                Text("Siguiente")
                            }
                        }
                    }
                }
            }
        }
    }
}
