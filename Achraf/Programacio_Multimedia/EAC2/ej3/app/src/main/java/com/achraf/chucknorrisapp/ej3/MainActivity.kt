// Estudiante: Achraf Bentamou Sbaihi
package com.achraf.chucknorrisapp.ej3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.achraf.chucknorrisapp.ej3.ui.theme.ChuckNorrisAppTheme
import com.achraf.chucknorrisapp.ej3.ui.theme.FondoOscuro

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChuckNorrisAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = FondoOscuro
                ) {
                    NavGraph()
                }
            }
        }
    }
}

