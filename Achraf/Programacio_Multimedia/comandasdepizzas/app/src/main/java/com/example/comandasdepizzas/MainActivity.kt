package com.example.comandasdepizzas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.comandasdepizzas.ui.theme.ComandasDePizzasTheme

// Constants per l'estat de les vistes (passos)
enum class AppScreen { Name, Quantity, PizzaType, Summary }

// ViewModel per guardar l'historial del cicle de vida de manera segura (No es perd al girar la pantalla)
class LifecycleViewModel : ViewModel() {
    private val _events = mutableStateListOf<String>()
    val events: List<String> get() = _events

    fun addEvent(event: String) {
        _events.add(event)
    }

    fun clearEvents() {
        _events.clear()
    }
}

class MainActivity : ComponentActivity() {

    private val lifecycleViewModel: LifecycleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycleEvent("onCreate")
        enableEdgeToEdge()
        setContent {
            ComandasDePizzasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().padding(top = 24.dp, bottom = 24.dp), // Margins needed due to edgeToEdge
                    color = MaterialTheme.colorScheme.background
                ) {
                    PizzaApp(lifecycleViewModel)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        logLifecycleEvent("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycleEvent("onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycleEvent("onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycleEvent("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycleEvent("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycleEvent("onRestart")
    }

    private fun logLifecycleEvent(event: String) {
        // Registre directe del sistema pel sistema visual de logs (Logcat)
        Log.d("LifecycleLog", event)
        // Guardar l'esdeveniment al model de vistes de Compose
        lifecycleViewModel.addEvent(event)
    }
}

@Composable
fun PizzaApp(lifecycleViewModel: LifecycleViewModel) {

    // ===== VARIABLE D'ESTAT PER LA PANTALLA ACTUAL =====
    var currentScreen by rememberSaveable { mutableStateOf(AppScreen.Name) }

    // ===== DADES DE LA COMANDA (també han de sobreviure per a l'UI final) =====
    var customerName by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var pizzaType by rememberSaveable { mutableStateOf("") }

    // ===== LES 3 VARIABLES DE L'EXERCICI PER COMPROVAR ELS SEUS COMPORTAMENTS =====
    var contadorNormal = 0
    var contadorRemember by remember { mutableStateOf(0) }
    var contadorSaveable by rememberSaveable { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // PANTALLES DE L'APP DE COMANDES
        Box(modifier = Modifier.weight(1f)) {
            when (currentScreen) {
                AppScreen.Name -> {
                    Column {
                        Text("1. Nom del client", style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = customerName,
                            onValueChange = { customerName = it },
                            label = { Text("Nom complet") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                AppScreen.Quantity -> {
                    Column {
                        Text("2. Quantitat de pizzes", style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = quantity,
                            onValueChange = { quantity = it },
                            label = { Text("Introduïu la quantitat") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                AppScreen.PizzaType -> {
                    val pizzaOptions = listOf("Margarita", "4 Formatges", "Barbacoa", "Vegetal")
                    Column {
                        Text("3. Tipus de pizza", style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(16.dp))
                        pizzaOptions.forEach { text ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .selectable(
                                        selected = (text == pizzaType),
                                        onClick = { pizzaType = text }
                                    )
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (text == pizzaType),
                                    onClick = { pizzaType = text }
                                )
                                Text(
                                    text = text,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                        }
                    }
                }
                AppScreen.Summary -> {
                    Column {
                        Text("4. Resum final", style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Nom del client: $customerName")
                        Text("Quantitat sol·licitada: $quantity")
                        Text("Tipus seleccionat: $pizzaType")
                    }
                }
            }
        }

        // CONTROL DE NAVEGACIÓ (BOTÓ SEGÜENT / REINICIAR)
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if (currentScreen != AppScreen.Summary) {
                // Lògica de validació
                val isNextEnabled = when (currentScreen) {
                    AppScreen.Name -> customerName.isNotBlank()
                    AppScreen.Quantity -> quantity.toIntOrNull()?.let { it > 0 } ?: false
                    AppScreen.PizzaType -> pizzaType.isNotBlank()
                    else -> false
                }

                Button(
                    onClick = {
                        currentScreen = when (currentScreen) {
                            AppScreen.Name -> AppScreen.Quantity
                            AppScreen.Quantity -> AppScreen.PizzaType
                            AppScreen.PizzaType -> AppScreen.Summary
                            AppScreen.Summary -> AppScreen.Summary
                        }
                    },
                    enabled = isNextEnabled,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Següent")
                }
            } else {
                Button(
                    onClick = {
                        // Buidar i tornar al pas principal
                        currentScreen = AppScreen.Name
                        customerName = ""
                        quantity = ""
                        pizzaType = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Reiniciar Comanda")
                }
            }
        }

        Divider()
        
        // PANTALLA DELS COMPTADORS 
        Column(modifier = Modifier.padding(vertical = 12.dp)) {
            Text("Proves de Comptador:", style = MaterialTheme.typography.titleMedium)
            Text("contadorNormal = $contadorNormal")
            Text("contadorRemember = $contadorRemember")
            Text("contadorSaveable = $contadorSaveable")
            
            Button(
                onClick = {
                    contadorNormal++
                    contadorRemember++
                    contadorSaveable++
                }, 
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("+1 comptadors (Afegir pizza)")
            }
        }

        Divider()

        // PANEL DEL CICLE DE VIDA
        LifecycleLogPanel(lifecycleViewModel)
    }
}

@Composable
fun LifecycleLogPanel(lifecycleViewModel: LifecycleViewModel) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Cicle de Vida (Activity)", style = MaterialTheme.typography.titleMedium)
            Button(onClick = { lifecycleViewModel.clearEvents() }) {
                Text("Reset historial")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        val events = lifecycleViewModel.events
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp) // Perquè la llista càpiga
        ) {
            items(events) { event ->
                Text(event, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}