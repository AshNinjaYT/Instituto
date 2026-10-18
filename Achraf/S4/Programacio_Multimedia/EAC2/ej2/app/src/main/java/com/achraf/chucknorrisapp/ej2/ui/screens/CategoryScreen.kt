package com.achraf.chucknorrisapp.ej2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.lifecycle.viewModelScope
import com.achraf.chucknorrisapp.ej2.data.api.RetrofitClient
import com.achraf.chucknorrisapp.ej2.data.model.ChuckNorrisJoke
import com.achraf.chucknorrisapp.ej2.ui.theme.*
import kotlinx.coroutines.launch

// --- ViewModel para la pantalla de Categorías ---
class CategoryViewModel : ViewModel() {
    // Variables con mutableStateOf para que Compose las escuche sin lios
    var joke by mutableStateOf<ChuckNorrisJoke?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
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
}

// --- Pantalla UI ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(category: String, viewModel: CategoryViewModel, onBackClick: () -> Unit) {
    // Solo cargamos la primera vez que entra a la categoría
    LaunchedEffect(category) {
        viewModel.loadJokeForCategory(category)
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
                        Button(
                            onClick = { viewModel.loadJokeForCategory(category) },
                            colors = ButtonDefaults.buttonColors(containerColor = ColorVerde)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = "Otro")
                            Spacer(Modifier.width(8.dp))
                            Text("Siguiente chiste")
                        }
                    }
                }
            }
        }
    }
}
