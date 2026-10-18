package com.achraf.chucknorrisapp.ej2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// Tema oscuro por defecto
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
