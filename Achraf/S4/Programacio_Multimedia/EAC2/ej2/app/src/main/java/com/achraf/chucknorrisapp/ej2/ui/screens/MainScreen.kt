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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.launch

// --- ViewModel principal ---
class MainViewModel : ViewModel() {
    
    // Variables simples para guardar el estado de la pantalla
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
                // Se conecta a internet
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
fun MainScreen(viewModel: MainViewModel, onCategoryClick: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chistes de Chuck Norris", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SuperficieOscura)
            )
        },
        containerColor = FondoOscuro
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            Text("Chiste Aleatorio", style = MaterialTheme.typography.headlineMedium, color = ColorNaranja)
            Spacer(modifier = Modifier.height(10.dp))
            
            // Carta donde sale el chiste
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SuperficieOscura),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Usamos las variables del viewModel directamente
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
                        Button(
                            onClick = { viewModel.loadRandomJoke() },   // Pedimos otro chiste
                            colors = ButtonDefaults.buttonColors(containerColor = ColorNaranja),
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = "Nuevo")
                            Spacer(Modifier.width(8.dp))
                            Text("Siguiente chiste")
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
