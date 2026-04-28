package com.achraf.chucknorrisapp.ej3.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
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
import com.achraf.chucknorrisapp.ej3.data.db.AppDatabase
import com.achraf.chucknorrisapp.ej3.data.db.FavoriteJoke
import com.achraf.chucknorrisapp.ej3.ui.theme.*
import kotlinx.coroutines.launch

// --- ViewModel para la pantalla de Favoritos ---
class FavoritesViewModel(private val db: AppDatabase) : ViewModel() {
    
    // Lista de chistes favoritos (usando mutableStateOf)
    var favorites by mutableStateOf<List<FavoriteJoke>>(emptyList())
        private set

    init {
        // Nada más cargar pedimos a la bbdd todos los favoritos
        viewModelScope.launch {
            db.favoriteJokeDao().getAllFavorites().collect { lista ->
                favorites = lista
            }
        }
    }

    // Borra un chiste de la lista
    fun deleteFavorite(joke: FavoriteJoke) {
        viewModelScope.launch {
            db.favoriteJokeDao().deleteFavorite(joke)
        }
    }

    companion object {
        fun factory(context: Context): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val db = AppDatabase.getInstance(context)
                    return FavoritesViewModel(db) as T
                }
            }
        }
    }
}

// --- Interfaz de Favoritos ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Favorite, contentDescription = null, tint = ColorRosa, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Chistes Favoritos", fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SuperficieOscura)
            )
        },
        containerColor = FondoOscuro
    ) { padding ->
        // Si no hay favoritos, mostramos un mensaje
        if (viewModel.favorites.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Favorite, contentDescription = null, tint = TextoGris, modifier = Modifier.size(64.dp))
                    Spacer(Modifier.height(16.dp))
                    Text("Aún no tienes chistes favoritos", style = MaterialTheme.typography.bodyLarge, color = TextoGris)
                }
            }
        } else {
            // Si hay, pintamos una lista
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewModel.favorites, key = { it.id }) { joke ->
                    FavoriteJokeCard(joke = joke, onDelete = { viewModel.deleteFavorite(joke) })
                }
            }
        }
    }
}

// Cartita de cada chiste guardado
@Composable
fun FavoriteJokeCard(joke: FavoriteJoke, onDelete: () -> Unit) {
    var showConfirmDialog by remember { mutableStateOf(false) }

    // Dialogo para cuando le damos a borrar
    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text("Eliminar") },
            text = { Text("¿Quieres borrar este chiste de favoritos?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete()
                        showConfirmDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = ColorRojo)
                ) {
                    Text("Borrar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmDialog = false }) {
                    Text("Cancelar", color = TextoBlanco)
                }
            },
            containerColor = SuperficieOscura
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SuperficieOscura),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (!joke.category.isNullOrBlank()) {
                Text(
                    text = translateCategory(joke.category),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorVerde
                )
                Spacer(Modifier.height(8.dp))
            }

            Text(
                text = "\"${joke.jokeText}\"",
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic,
                lineHeight = 22.sp,
                color = TextoBlanco
            )

            Spacer(Modifier.height(12.dp))

            // Botón de eliminar
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(
                    onClick = { showConfirmDialog = true },
                    colors = ButtonDefaults.textButtonColors(contentColor = ColorRojo)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Borrar", modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Borrar", fontSize = 13.sp)
                }
            }
        }
    }
}
