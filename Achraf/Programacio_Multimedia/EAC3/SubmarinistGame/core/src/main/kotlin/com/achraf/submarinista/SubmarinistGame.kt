package com.achraf.submarinista

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.achraf.submarinista.screens.MenuScreen

/** 
 * Clase principal del juego. Hereda de Game para poder usar múltiples pantallas (Screens).
 */
class SubmarinistGame : Game() {
    
    // El SpriteBatch se comparte para ahorrar memoria
    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()
        // Inicializamos y mostramos la pantalla de menú principal
        setScreen(MenuScreen(this))
    }

    override fun dispose() {
        super.dispose() // Muy importante llamar al super.dispose() para que limpie la pantalla actual
        batch.dispose()
    }
}
