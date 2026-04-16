package com.achraf.chucknorrisapp.ej3.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// Tema oscuro por defecto (igual que ej2)
private val EsquemaColores = darkColorScheme(
    primary = ColorNaranja,
    secondary = ColorVerde,
    background = FondoOscuro,
    surface = SuperficieOscura,
    onBackground = TextoBlanco,
    onSurface = TextoBlanco
)

@Composable
fun ChuckNorrisAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EsquemaColores,
        typography = Typography,
        content = content
    )
}
