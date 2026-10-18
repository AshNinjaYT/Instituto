package com.achraf.submarinista.entities

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

// Aquí defino qué cosas podemos recoger del mapa
enum class ItemType { TREASURE, OXYGEN }

// Esta clase es para las monedas y las burbujas de aire
class Item(val type: ItemType, val position: Vector2, val region: TextureRegion) {
    val size = 32f // Tamaño estándar de 32x32 para los objetos
    private val bounds = Rectangle(position.x, position.y, size, size)
    
    // Con esto compruebo si el jugador toca el objeto
    fun getBoundingRectangle(): Rectangle {
        return bounds
    }
}
