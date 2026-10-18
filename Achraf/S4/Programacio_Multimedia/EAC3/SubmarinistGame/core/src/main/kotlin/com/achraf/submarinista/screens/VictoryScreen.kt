package com.achraf.submarinista.screens

import com.achraf.submarinista.SubmarinistGame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.viewport.Viewport

// Pantalla que sale cuando ganas la partida (llegas al final)
class VictoryScreen(val game: SubmarinistGame, val score: Int, val time: Float, val treasures: Int) : Screen {

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(800f, 600f, camera)
    private val font = BitmapFont()
    private var timer = 0f // Para evitar saltar la pantalla por accidente
    
    // Colores para la interfaz
    private val colorTitulo = Color.GOLD
    private val colorSombra = Color.BLACK
    private val colorPuntos = Color.YELLOW
    private val colorTesoros = Color.YELLOW
    private val colorTiempo = Color.CYAN
    private val colorMensaje = Color.WHITE
    private val colorVolver = Color.WHITE

    init {
        font.data.setScale(2.5f)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0.4f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        viewport.apply()
        camera.update()
        game.batch.projectionMatrix = camera.combined

        timer += delta
        game.batch.begin()

        // Título de victoria
        font.data.setScale(3.5f)
        drawTextWithShadow("¡VICTORIA!", 500f, colorTitulo)

        // Estadísticas finales
        font.data.setScale(1.8f)
        drawTextWithShadow("¡Has recogido todos los tesoros!", 400f, colorMensaje)
        drawTextWithShadow("Puntuación final: $score", 320f, colorPuntos)
        drawTextWithShadow("Tesoros recogidos: $treasures", 270f, colorTesoros)
        drawTextWithShadow("Tiempo total: ${time.toInt()} segundos", 220f, colorTiempo)
        
        if (timer > 3f) {
            font.data.setScale(1.5f)
            drawTextWithShadow("Toca para volver al menú", 80f, colorVolver)
        }
        game.batch.end()

        if (timer > 3f && Gdx.input.isTouched) {
            game.screen = MenuScreen(game)
        }
    }

    /**
     * Función auxiliar para dibujar texto con un efecto de sombra (negro desplazado).
     * Evita tener que repetir el código de dibujado y los strings.
     */
    private fun drawTextWithShadow(text: String, y: Float, colorPrincipal: Color) {
        // Dibujamos la sombra (negro) un poco desplazada
        font.color = colorSombra
        font.draw(game.batch, text, 3f, y - 3f, 800f, Align.center, false)
        
        // Dibujamos el texto real encima
        font.color = colorPrincipal
        font.draw(game.batch, text, 0f, y, 800f, Align.center, false)
    }

    // Estos métodos son obligatorios por la interfaz Screen de LibGDX, aunque no los usemos en esta pantalla
    override fun show() {}
    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}

    override fun dispose() {
        font.dispose()
    }
}
