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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.comandasdepizzas.ui.theme.ComandasDePizzasTheme

// Constants per l'estat de les vistes (passos antics - es mantenen per llegibilitat o referència, però no s'usaran al Navigation)
enum class AppScreen { Name, Quantity, PizzaType, Summary }

// ViewModel per guardar l'estat de la comanda (Requisit Ex. 3)
class PizzaOrderViewModel : ViewModel() {
    var customerName by mutableStateOf("")
        private set
    var quantity by mutableIntStateOf(1)
        private set
    var pizzaType by mutableStateOf("")
        private set

    fun updateName(name: String) { customerName = name }
    fun updateQuantity(qty: Int) { quantity = qty }
    fun updatePizzaType(type: String) { pizzaType = type }

    fun resetOrder() {
        customerName = ""
        quantity = 1
        pizzaType = ""
    }
}

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
    private val pizzaOrderViewModel: PizzaOrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycleEvent("onCreate")
        enableEdgeToEdge()
        setContent {
            ComandasDePizzasTheme {
                MainApp(lifecycleViewModel, pizzaOrderViewModel)
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
fun MainApp(lifecycleViewModel: LifecycleViewModel, pizzaOrderViewModel: PizzaOrderViewModel) {
    val navController = rememberNavController()
    
    // Determinar el títol de la TopAppBar
    val destiActual by navController.currentBackStackEntryAsState()
    val currentRoute = destiActual?.destination?.route ?: "inici"
    val titolPantalla = when (currentRoute) {
        "inici" -> "Bienvenida"
        "comanda" -> "Realizar Pedido"
        "resum" -> "Resumen del Pedido"
        else -> "App de Pizzas"
    }

    // ===== LAS 3 VARIABLES DEL EJERCICIO PARA COMPROBAR COMPORTAMIENTOS =====
    var contadorNormal = 0
    var contadorRemember by remember { mutableIntStateOf(0) }
    var contadorSaveable by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text(titolPantalla) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // NavHost ocupa el rest del espai
            NavHost(
                navController = navController,
                startDestination = "inici",
                modifier = Modifier.weight(1f)
            ) {
                composable("inici") { PantallaInici(navController) }
                composable("comanda") { PantallaComanda(navController, pizzaOrderViewModel) }
                composable("resum") { PantallaResum(navController, pizzaOrderViewModel) }
            }

            HorizontalDivider()
            
            // PANTALLA DE LOS CONTADORES 
            Column(modifier = Modifier.padding(vertical = 12.dp)) {
                Text("Pruebas de Contadores:", style = MaterialTheme.typography.titleMedium)
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
                    Text("+1 contadores (Añadir pizza)")
                }
            }

            HorizontalDivider()

            // PANEL DEL CICLO DE VIDA
            LifecycleLogPanel(lifecycleViewModel)
        }
    }
}

@Composable
fun PantallaInici(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🍕 Bienvenido a la Pizzería", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("comanda") },
            modifier = Modifier.fillMaxWidth(0.8f).height(50.dp)
        ) {
            Text("Comenzar pedido", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun PantallaComanda(navController: NavController, viewModel: PizzaOrderViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Datos introducidos por el usuario
        Text("Datos del cliente", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = viewModel.customerName,
            onValueChange = { viewModel.updateName(it) },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("Cantidad de pizzas", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { if (viewModel.quantity > 1) viewModel.updateQuantity(viewModel.quantity - 1) }
            ) { Text("-") }
            
            Text(
                text = viewModel.quantity.toString(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            
            Button(
                onClick = { viewModel.updateQuantity(viewModel.quantity + 1) }
            ) { Text("+") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        val pizzaOptions = listOf("Margarita", "4 Quesos", "Barbacoa", "Vegetal")
        Text("Tipo de pizza", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        pizzaOptions.forEach { text ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == viewModel.pizzaType),
                        onClick = { viewModel.updatePizzaType(text) }
                    )
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == viewModel.pizzaType),
                    onClick = { viewModel.updatePizzaType(text) }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Lógica de validación requerida
        val isNextEnabled = viewModel.customerName.isNotBlank() && 
                            viewModel.quantity >= 1 && 
                            viewModel.pizzaType.isNotBlank()

        Button(
            onClick = { navController.navigate("resum") },
            enabled = isNextEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver resumen")
        }
    }
}

@Composable
fun PantallaResum(navController: NavController, viewModel: PizzaOrderViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Resumen final", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nombre del cliente: ${viewModel.customerName}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Cantidad solicitada: ${viewModel.quantity}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Tipo seleccionado: ${viewModel.pizzaType}", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = { /* Lógica ficticia de confirmar envío */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pedido confirmado")
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = {
                // Limpiar los datos
                viewModel.resetOrder()
                // Volver a la pantalla de inicio
                navController.navigate("inici") {
                    popUpTo("inici") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reiniciar Pedido (Volver a Inicio)")
        }
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
            Text("Ciclo de Vida (Activity)", style = MaterialTheme.typography.titleMedium)
            Button(onClick = { lifecycleViewModel.clearEvents() }) {
                Text("Resetear historial")
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PantallaIniciPreview() {
    ComandasDePizzasTheme {
        PantallaInici(navController = rememberNavController())
    }
}