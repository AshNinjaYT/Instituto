package com.achraf.chucknorrisapp.ej3.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import com.achraf.chucknorrisapp.ej3.data.*
import com.achraf.chucknorrisapp.ej3.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// --- ViewModel ---
class CategoryViewModel(private val db: AppDatabase) : ViewModel() {

    private val _joke = MutableStateFlow<ChuckNorrisJoke?>(null)
    val joke: StateFlow<ChuckNorrisJoke?> = _joke.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _favoriteMessage = MutableStateFlow<String?>(null)
    val favoriteMessage: StateFlow<String?> = _favoriteMessage.asStateFlow()

    fun loadJokeForCategory(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = RetrofitClient.api.getRandomJokeByCategory(category)
                _joke.value = result
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar chiste de categoría: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun saveCurrentJokeAsFavorite(category: String) {
        val joke = _joke.value ?: return
        viewModelScope.launch {
            val dao = db.favoriteJokeDao()
            val existing = dao.getFavoriteByApiId(joke.id)
            if (existing != null) {
                _favoriteMessage.value = "Este chiste ya está en favoritos"
            } else {
                dao.insertFavorite(
                    FavoriteJoke(
                        jokeApiId = joke.id,
                        jokeText = joke.value,
                        category = category
                    )
                )
                _favoriteMessage.value = "¡Chiste guardado en favoritos!"
            }
        }
    }

    fun clearFavoriteMessage() {
        _favoriteMessage.value = null
    }

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

// --- UI ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    category: String,
    viewModel: CategoryViewModel,
    onBackClick: () -> Unit
) {
    val joke by viewModel.joke.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val favoriteMessage by viewModel.favoriteMessage.collectAsState()

    LaunchedEffect(category) {
        viewModel.loadJokeForCategory(category)
    }

    LaunchedEffect(favoriteMessage) {
        if (favoriteMessage != null) {
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
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
            )
        },
        containerColor = DarkBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = favoriteMessage != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = AccentFavorite),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = favoriteMessage ?: "",
                        modifier = Modifier.padding(12.dp),
                        color = TextPrimary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(color = AccentSecondary)
                    } else if (joke != null) {
                        Text(
                            text = "\"${joke!!.value}\"",
                            style = MaterialTheme.typography.bodyLarge,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 28.sp,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        OutlinedButton(
                            onClick = { viewModel.saveCurrentJokeAsFavorite(category) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = AccentFavorite),
                            border = androidx.compose.foundation.BorderStroke(1.dp, AccentFavorite)
                        ) {
                            Icon(Icons.Default.Favorite, contentDescription = "Añadir a favoritos", modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Añadir a favoritos")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = { viewModel.loadJokeForCategory(category) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = AccentSecondary)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = "Otro", modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Siguiente chiste")
                        }
                    }
                }
            }
        }
    }
}
