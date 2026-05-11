package com.achraf.submarinista

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport

/**
 * Pantalla que se muestra al llegar al final del mapa.
 */
class VictoryScreen(val game: SubmarinistGame, val score: Int, val time: Float, val treasures: Int) : Screen {

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(800f, 600f, camera)
    private val font = BitmapFont()
    private var timer = 0f // Para evitar saltar la pantalla por accidente

    init {
        font.data.setScale(2.5f)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0.4f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        viewport.apply()
        game.batch.projectionMatrix = camera.combined

        timer += delta
        game.batch.begin()

        // Función interna para sombra
        fun drawShadowText(text: String, x: Float, y: Float, color: com.badlogic.gdx.graphics.Color, scale: Float = 1.8f) {
            font.data.setScale(scale)
            font.color = com.badlogic.gdx.graphics.Color.BLACK
            font.draw(game.batch, text, x + 3, y - 3, 800f, Align.center, false)
            font.color = color
            font.draw(game.batch, text, x, y, 800f, Align.center, false)
        }

        drawShadowText("¡VICTORIA!", 0f, 500f, com.badlogic.gdx.graphics.Color.GOLD, 3.5f)
        drawShadowText("Has explorado todo el océano", 0f, 400f, com.badlogic.gdx.graphics.Color.WHITE)
        drawShadowText("Puntuación final: $score", 0f, 320f, com.badlogic.gdx.graphics.Color.YELLOW)
        drawShadowText("Tesoros recogidos: $treasures", 0f, 270f, com.badlogic.gdx.graphics.Color.YELLOW)
        drawShadowText("Tiempo total: ${time.toInt()} segundos", 0f, 220f, com.badlogic.gdx.graphics.Color.CYAN)
        
        if (timer > 3f) {
            drawShadowText("Toca para volver al menú", 0f, 80f, com.badlogic.gdx.graphics.Color.WHITE, 1.5f)
        }
        game.batch.end()

        if (timer > 3f && Gdx.input.isTouched) {
            game.screen = MenuScreen(game)
            dispose()
        }
    }

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
