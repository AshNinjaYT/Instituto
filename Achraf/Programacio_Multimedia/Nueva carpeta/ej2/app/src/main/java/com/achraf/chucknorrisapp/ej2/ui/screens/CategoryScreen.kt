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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// --- ViewModel ---
class CategoryViewModel : ViewModel() {
    private val _joke = MutableStateFlow<ChuckNorrisJoke?>(null)
    val joke: StateFlow<ChuckNorrisJoke?> = _joke.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

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
}

// --- UI ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(category: String, viewModel: CategoryViewModel, onBackClick: () -> Unit) {
    val joke by viewModel.joke.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(category) {
        viewModel.loadJokeForCategory(category)
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
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
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
                        Button(
                            onClick = { viewModel.loadJokeForCategory(category) },
                            colors = ButtonDefaults.buttonColors(containerColor = AccentSecondary)
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

