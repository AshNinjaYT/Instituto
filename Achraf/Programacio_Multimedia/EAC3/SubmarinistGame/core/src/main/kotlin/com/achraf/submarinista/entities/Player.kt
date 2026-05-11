package com.achraf.submarinista.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

/**
 * Clase que representa al jugador (submarinista).
 * Controla su posición, velocidad (flotabilidad y gravedad) y rectángulo de colisión.
 */
class Player(startX: Float, startY: Float) {
    var position = Vector2(startX, startY)
    var velocity = Vector2(0f, 0f)
    private val bounds = Rectangle() // Pre-alojamos el rectángulo para evitar lag (GC pressure)
    
    
    // Tamaño del jugador (64x64 encaja mejor en tiles de 32x32)
    val size = 64f    
    
    // La gravedad es lo rápido que cae cuando no impulsamos
    val gravity = 300f
    
    // La fuerza de empuje hacia arriba al presionar la tecla
    val jumpImpulse = 350f
    
    // Velocidad de movimiento a izquierda/derecha
    val speedX = 200f

    // Atributos del jugador para el Apartado 3, 5 y 7
    var score = 0
    var treasuresCollected = 0
    var timeSurvived = 0f
    var oxygen = 100f
    
    // Estados posibles del jugador para las animaciones
    enum class State {
        IDLE, UP, DOWN
    }
    
    var state = State.IDLE
    var stateTime = 0f // Lleva la cuenta del tiempo para saber qué frame dibujar

    fun update(delta: Float) {
        // Sumamos el tiempo que ha pasado al reloj de animaciones y supervivencia
        stateTime += delta
        timeSurvived += delta

        // --- 1. GESTIÓN DE OXÍGENO (Apartado 5 adelantado) ---
        // El oxígeno baja un poco cada segundo (2 unidades por segundo)
        oxygen -= 2f * delta
        if (oxygen < 0f) oxygen = 0f

        // --- 2. GESTIÓN DE ENTRADA (Móvil y Desktop) ---
        
        val isTouched = Gdx.input.isTouched
        val isUpPressed = Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)
        val isLeftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)
        val isRightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)

        // MOVIMIENTO VERTICAL (Impulso)
        if (isTouched || isUpPressed) {
            // Empujamos con más fuerza para compensar la gravedad
            velocity.y += 700f * delta
            if (velocity.y > 250f) velocity.y = 250f // Aumentado de 180 a 250
            state = State.UP
        } else {
            // Al soltar, la gravedad nos frena más rápido
            velocity.y -= gravity * delta
            if (velocity.y < -200f) velocity.y = -200f // Aumentado de -150 a -200
            state = if (velocity.y < -20f) State.DOWN else State.IDLE
        }
        
        // MOVIMIENTO HORIZONTAL
        if (isLeftPressed) {
            velocity.x = -speedX
        } else if (isRightPressed) {
            velocity.x = speedX
        } else if (isTouched) {
            // En móvil, si tocas la mitad izquierda vas a la izquierda, y derecha a la derecha
            if (Gdx.input.x < Gdx.graphics.width / 2) {
                velocity.x = -speedX
            } else {
                velocity.x = speedX
            }
        } else {
            velocity.x = 0f
        }
    }

    /**
     * Devuelve el rectángulo que rodea al jugador, necesario para comprobar colisiones con el mapa.
     * Usamos un rectángulo un poco más pequeño que la imagen para que el movimiento sea más fluido
     * y no se quede "pegado" a las esquinas.
     */
    fun getBoundingRectangle(): Rectangle {
        // Usamos un margen generoso (16px por cada lado).
        // Si el submarino es de 64x64, la zona que choca es de 32x32.
        // Esto hace que el juego sea mucho más permisivo y fácil de controlar.
        val margen = 16f
        bounds.set(
            position.x + margen, 
            position.y + margen, 
            size - (margen * 2), 
            size - (margen * 2)
        )
        return bounds
    }
}