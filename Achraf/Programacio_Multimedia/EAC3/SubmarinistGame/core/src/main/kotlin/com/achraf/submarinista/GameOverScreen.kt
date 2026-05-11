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
 * Pantalla que se muestra al perder (sin oxígeno).
 */
class GameOverScreen(val game: SubmarinistGame, val score: Int, val time: Float, val treasures: Int) : Screen {

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(800f, 600f, camera)
    private val font = BitmapFont()
    private var timer = 0f // Para evitar saltar la pantalla por accidente

    init {
        font.data.setScale(2.5f) // Más grande para el título
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.2f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        viewport.apply()
        game.batch.projectionMatrix = camera.combined

        timer += delta // Acumulamos tiempo

        game.batch.begin()
        
        // Función interna para sombra
        fun drawShadowText(text: String, x: Float, y: Float, color: com.badlogic.gdx.graphics.Color, scale: Float = 1.8f) {
            font.data.setScale(scale)
            font.color = com.badlogic.gdx.graphics.Color.BLACK
            font.draw(game.batch, text, x + 3, y - 3, 800f, Align.center, false)
            font.color = color
            font.draw(game.batch, text, x, y, 800f, Align.center, false)
        }

        drawShadowText("GAME OVER", 0f, 480f, com.badlogic.gdx.graphics.Color.RED, 3f)
        drawShadowText("Puntuación final: $score", 0f, 350f, com.badlogic.gdx.graphics.Color.WHITE)
        drawShadowText("Tesoros recogidos: $treasures", 0f, 300f, com.badlogic.gdx.graphics.Color.YELLOW)
        drawShadowText("Tiempo sobrevivido: ${time.toInt()} segundos", 0f, 250f, com.badlogic.gdx.graphics.Color.CYAN)
        
        if (timer > 3f) {
            drawShadowText("Toca para reintentar", 0f, 100f, com.badlogic.gdx.graphics.Color.WHITE, 1.5f)
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
