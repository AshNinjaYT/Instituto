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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.comandasdepizzas.ui.theme.ComandasDePizzasTheme

// ViewModel para guardar los datos del pedido (compartido entre pantallas)
class PizzaOrderViewModel : ViewModel() {
    var nombreCliente by mutableStateOf("")
    var cantidad by mutableIntStateOf(1)
    var tipoPizza by mutableStateOf("")

    fun resetear() {
        nombreCliente = ""
        cantidad = 1
        tipoPizza = ""
    }
}

// ViewModel para el historial del ciclo de vida
class LifecycleViewModel : ViewModel() {
    val eventos = mutableStateListOf<String>()

    fun añadirEvento(evento: String) {
        eventos.add(evento)
    }

    fun limpiar() {
        eventos.clear()
    }
}

class MainActivity : ComponentActivity() {

    val lifecycleVM: LifecycleViewModel by viewModels()
    val pedidoVM: PizzaOrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        guardarEvento("onCreate")
        enableEdgeToEdge()
        setContent {
            ComandasDePizzasTheme {
                AppPizzas(lifecycleVM, pedidoVM)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        guardarEvento("onStart")
    }

    override fun onResume() {
        super.onResume()
        guardarEvento("onResume")
    }

    override fun onPause() {
        super.onPause()
        guardarEvento("onPause")
    }

    override fun onStop() {
        super.onStop()
        guardarEvento("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        guardarEvento("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        guardarEvento("onRestart")
    }

    fun guardarEvento(evento: String) {
        Log.d("Ciclo", evento)
        lifecycleVM.añadirEvento(evento)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppPizzas(lifecycleVM: LifecycleViewModel, pedidoVM: PizzaOrderViewModel) {

    val navController = rememberNavController()

    // Saber en qué pantalla estamos para poner el título
    val entrada by navController.currentBackStackEntryAsState()
    val ruta = entrada?.destination?.route ?: "inicio"
    val titulo = when (ruta) {
        "inicio" -> "Inicio"
        "pedido" -> "Hacer Pedido"
        "resumen" -> "Resumen"
        else -> "Pizzería"
    }

    // 3 variables para comparar comportamiento de estado
    var contadorNormal = 0
    var contadorRemember by remember { mutableIntStateOf(0) }
    var contadorSaveable by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(titulo) })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Navegación entre pantallas
            NavHost(
                navController = navController,
                startDestination = "inicio",
                modifier = Modifier.weight(1f)
            ) {
                composable("inicio") { PantallaInicio(navController) }
                composable("pedido") { PantallaPedido(navController, pedidoVM) }
                composable("resumen") { PantallaResumen(navController, pedidoVM) }
            }

            HorizontalDivider()

            // Sección de contadores
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text("Contadores:")
                Text("contadorNormal = $contadorNormal")
                Text("contadorRemember = $contadorRemember")
                Text("contadorSaveable = $contadorSaveable")
                Button(onClick = {
                    contadorNormal++
                    contadorRemember++
                    contadorSaveable++
                }) {
                    Text("+1 a todos")
                }
            }

            HorizontalDivider()

            // Historial del ciclo de vida
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Ciclo de Vida:")
                    Button(onClick = { lifecycleVM.limpiar() }) {
                        Text("Limpiar")
                    }
                }
                LazyColumn(modifier = Modifier.height(100.dp)) {
                    items(lifecycleVM.eventos) { evento ->
                        Text(evento)
                    }
                }
            }
        }
    }
}

@Composable
fun PantallaInicio(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido a la Pizzería")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("pedido") }) {
            Text("Comenzar pedido")
        }
    }
}

@Composable
fun PantallaPedido(navController: NavController, pedidoVM: PizzaOrderViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Nombre del cliente:")
        OutlinedTextField(
            value = pedidoVM.nombreCliente,
            onValueChange = { pedidoVM.nombreCliente = it },
            label = { Text("Tu nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Cantidad de pizzas:")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = {
                if (pedidoVM.cantidad > 1) pedidoVM.cantidad--
            }) {
                Text("-")
            }
            Text(
                text = "${pedidoVM.cantidad}",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Button(onClick = { pedidoVM.cantidad++ }) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Tipo de pizza:")
        val tipos = listOf("Margarita", "4 Quesos", "Barbacoa", "Vegetal")
        tipos.forEach { tipo ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (tipo == pedidoVM.tipoPizza),
                        onClick = { pedidoVM.tipoPizza = tipo }
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (tipo == pedidoVM.tipoPizza),
                    onClick = { pedidoVM.tipoPizza = tipo }
                )
                Text(tipo)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // El botón solo se activa si los datos son correctos
        val puedeAvanzar = pedidoVM.nombreCliente.isNotBlank() && pedidoVM.tipoPizza.isNotBlank()
        Button(
            onClick = { navController.navigate("resumen") },
            enabled = puedeAvanzar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver resumen")
        }
    }
}

@Composable
fun PantallaResumen(navController: NavController, pedidoVM: PizzaOrderViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Resumen del pedido:")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Nombre: ${pedidoVM.nombreCliente}")
        Text("Cantidad: ${pedidoVM.cantidad}")
        Text("Pizza: ${pedidoVM.tipoPizza}")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* pedido confirmado */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirmar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                pedidoVM.resetear()
                navController.navigate("inicio") {
                    popUpTo("inicio") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reiniciar")
        }
    }
}