package com.achraf.chucknorrisapp.ej2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achraf.chucknorrisapp.ej2.data.api.RetrofitClient
import com.achraf.chucknorrisapp.ej2.data.model.ChuckNorrisJoke
import com.achraf.chucknorrisapp.ej2.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// --- ViewModel ---
class MainViewModel : ViewModel() {
    private val _randomJoke = MutableStateFlow<ChuckNorrisJoke?>(null)
    val randomJoke: StateFlow<ChuckNorrisJoke?> = _randomJoke.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()

    private val _isLoadingJoke = MutableStateFlow(false)
    val isLoadingJoke: StateFlow<Boolean> = _isLoadingJoke.asStateFlow()

    private val _isLoadingCategories = MutableStateFlow(false)
    val isLoadingCategories: StateFlow<Boolean> = _isLoadingCategories.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadRandomJoke()
        loadCategories()
    }

    fun loadRandomJoke() {
        viewModelScope.launch {
            _isLoadingJoke.value = true
            _errorMessage.value = null
            try {
                val joke = RetrofitClient.api.getRandomJoke()
                _randomJoke.value = joke
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar el chiste: ${e.message}"
            } finally {
                _isLoadingJoke.value = false
            }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _isLoadingCategories.value = true
            try {
                val cats = RetrofitClient.api.getCategories()
                _categories.value = cats
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar categorías: ${e.message}"
            } finally {
                _isLoadingCategories.value = false
            }
        }
    }
}

// --- Tránsito de Categorías (Local) ---
fun translateCategory(category: String): String {
    val translations = mapOf(
        "animal" to "Animales",
        "career" to "Trabajo",
        "celebrity" to "Celebridades",
        "dev" to "Desarrollo",
        "explicit" to "Explícito",
        "fashion" to "Moda",
        "food" to "Comida",
        "history" to "Historia",
        "money" to "Dinero",
        "movie" to "Cine",
        "music" to "Música",
        "political" to "Política",
        "religion" to "Religión",
        "science" to "Ciencia",
        "sport" to "Deportes",
        "travel" to "Viajes"
    )
    return translations[category.lowercase()] ?: category.replaceFirstChar { it.uppercase() }
}

// --- UI ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, onCategoryClick: (String) -> Unit) {
    val joke by viewModel.randomJoke.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val isLoadingJoke by viewModel.isLoadingJoke.collectAsState()
    val isLoadingCats by viewModel.isLoadingCategories.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chistes de Chuck Norris", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
            )
        },
        containerColor = DarkBackground
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            Text("Chiste Aleatorio", style = MaterialTheme.typography.headlineMedium, color = AccentPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            
            // Random Joke Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (isLoadingJoke) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally), color = AccentPrimary)
                    } else if (joke != null) {
                        Text(
                            text = "\"${joke!!.value}\"",
                            style = MaterialTheme.typography.bodyLarge,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 24.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.loadRandomJoke() },
                            colors = ButtonDefaults.buttonColors(containerColor = AccentPrimary),
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = "Nuevo")
                            Spacer(Modifier.width(8.dp))
                            Text("Siguiente chiste")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Categorías", style = MaterialTheme.typography.headlineMedium, color = AccentSecondary)
            Spacer(modifier = Modifier.height(8.dp))
            
            if (isLoadingCats) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally), color = AccentSecondary)
            } else {
                LazyVerticalGrid(
                    modifier = Modifier.weight(1f),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        CategoryCard(category) { onCategoryClick(category) }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryCard(category: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(DarkSurface)
            .clickable { onClick() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = translateCategory(category),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
    }
}

