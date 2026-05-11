package com.achraf.submarinista.entities

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

// Tipos de objetos que podemos recoger
enum class ItemType { TREASURE, OXYGEN }

// Esta clase sirve para crear las monedas y el aire en el mapa
class Item(val type: ItemType, val position: Vector2, val region: TextureRegion) {
    val size = 32f
    private val bounds = Rectangle(position.x, position.y, size, size)
    
    fun getBoundingRectangle(): Rectangle {
        return bounds
    }
}
