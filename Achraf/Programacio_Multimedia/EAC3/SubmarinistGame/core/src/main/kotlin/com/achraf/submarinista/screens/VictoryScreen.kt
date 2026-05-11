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

        // Hago las sombras manualmente dibujando en negro un poco desplazado
        // y luego dibujo el texto normal encima con color
        
        font.data.setScale(3.5f)
        font.color = Color.BLACK
        font.draw(game.batch, "¡VICTORIA!", 3f, 497f, 800f, Align.center, false)
        font.color = Color.GOLD
        font.draw(game.batch, "¡VICTORIA!", 0f, 500f, 800f, Align.center, false)

        font.data.setScale(1.8f)
        
        font.color = Color.BLACK
        font.draw(game.batch, "Has explorado todo el océano", 3f, 397f, 800f, Align.center, false)
        font.color = Color.WHITE
        font.draw(game.batch, "Has explorado todo el océano", 0f, 400f, 800f, Align.center, false)
        
        font.color = Color.BLACK
        font.draw(game.batch, "Puntuación final: $score", 3f, 317f, 800f, Align.center, false)
        font.color = Color.YELLOW
        font.draw(game.batch, "Puntuación final: $score", 0f, 320f, 800f, Align.center, false)
        
        font.color = Color.BLACK
        font.draw(game.batch, "Tesoros recogidos: $treasures", 3f, 267f, 800f, Align.center, false)
        font.color = Color.YELLOW
        font.draw(game.batch, "Tesoros recogidos: $treasures", 0f, 270f, 800f, Align.center, false)
        
        font.color = Color.BLACK
        font.draw(game.batch, "Tiempo total: ${time.toInt()} segundos", 3f, 217f, 800f, Align.center, false)
        font.color = Color.CYAN
        font.draw(game.batch, "Tiempo total: ${time.toInt()} segundos", 0f, 220f, 800f, Align.center, false)
        
        if (timer > 3f) {
            font.data.setScale(1.5f)
            font.color = Color.BLACK
            font.draw(game.batch, "Toca para volver al menú", 3f, 77f, 800f, Align.center, false)
            font.color = Color.WHITE
            font.draw(game.batch, "Toca para volver al menú", 0f, 80f, 800f, Align.center, false)
        }
        game.batch.end()

        if (timer > 3f && Gdx.input.isTouched) {
            game.screen = MenuScreen(game)
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
