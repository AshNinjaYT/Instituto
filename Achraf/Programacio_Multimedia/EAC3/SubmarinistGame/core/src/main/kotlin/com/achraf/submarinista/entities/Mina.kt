package com.achraf.submarinista.entities

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

// Estados de la mina
enum class MinaState { ACTIVE, EXPLODING, DONE }

// Clase para las minas enemigas
class Mina(val startPosition: Vector2, val region: TextureRegion) {
    val position = Vector2(startPosition)
    val size = 45f // Tamaño del sprite en el juego
    var state = MinaState.ACTIVE
    var stateTime = 0f
    var explosionTime = 0f
    
    // Configuración del balanceo
    val range = 60f
    val speed = MathUtils.random(1f, 2f)
    val offset = MathUtils.random(0f, 6.28f)

    private val bounds = Rectangle()

    fun update(delta: Float, explosionAnim: Animation<TextureRegion>) {
        stateTime += delta
        
        if (state == MinaState.ACTIVE) {
            // Movimiento sinusoidal para que floten
            position.y = startPosition.y + MathUtils.sin(stateTime * speed + offset) * range
        } else if (state == MinaState.EXPLODING) {
            // Si está explotando, sumamos tiempo a la animación de explosión
            explosionTime += delta
            if (explosionAnim.isAnimationFinished(explosionTime)) {
                state = MinaState.DONE
            }
        }
    }
    
    fun getBoundingRectangle(): Rectangle {
        bounds.set(position.x, position.y, size, size)
        return bounds
    }
}
