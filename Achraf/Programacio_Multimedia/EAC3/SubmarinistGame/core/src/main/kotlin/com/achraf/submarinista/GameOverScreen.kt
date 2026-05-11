package com.achraf.submarinista

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport

// Pantalla que sale cuando mueres por falta de oxígeno
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
        
        // Hago las sombras manualmente dibujando en negro un poco desplazado
        // y luego dibujo el texto normal encima con color
        
        font.data.setScale(3f)
        font.color = com.badlogic.gdx.graphics.Color.BLACK
        font.draw(game.batch, "GAME OVER", 3f, 477f, 800f, Align.center, false)
        font.color = com.badlogic.gdx.graphics.Color.RED
        font.draw(game.batch, "GAME OVER", 0f, 480f, 800f, Align.center, false)

        font.data.setScale(1.8f)
        
        font.color = com.badlogic.gdx.graphics.Color.BLACK
        font.draw(game.batch, "Puntuación final: $score", 3f, 347f, 800f, Align.center, false)
        font.color = com.badlogic.gdx.graphics.Color.WHITE
        font.draw(game.batch, "Puntuación final: $score", 0f, 350f, 800f, Align.center, false)
        
        font.color = com.badlogic.gdx.graphics.Color.BLACK
        font.draw(game.batch, "Tesoros recogidos: $treasures", 3f, 297f, 800f, Align.center, false)
        font.color = com.badlogic.gdx.graphics.Color.YELLOW
        font.draw(game.batch, "Tesoros recogidos: $treasures", 0f, 300f, 800f, Align.center, false)
        
        font.color = com.badlogic.gdx.graphics.Color.BLACK
        font.draw(game.batch, "Tiempo sobrevivido: ${time.toInt()} segundos", 3f, 247f, 800f, Align.center, false)
        font.color = com.badlogic.gdx.graphics.Color.CYAN
        font.draw(game.batch, "Tiempo sobrevivido: ${time.toInt()} segundos", 0f, 250f, 800f, Align.center, false)
        
        if (timer > 3f) {
            font.data.setScale(1.5f)
            font.color = com.badlogic.gdx.graphics.Color.BLACK
            font.draw(game.batch, "Toca para reintentar", 3f, 97f, 800f, Align.center, false)
            font.color = com.badlogic.gdx.graphics.Color.WHITE
            font.draw(game.batch, "Toca para reintentar", 0f, 100f, 800f, Align.center, false)
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
