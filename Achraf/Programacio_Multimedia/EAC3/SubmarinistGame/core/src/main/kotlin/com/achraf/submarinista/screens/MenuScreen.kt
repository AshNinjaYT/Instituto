package com.achraf.submarinista.screens

import com.achraf.submarinista.SubmarinistGame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport

/**
 * Pantalla de inicio del juego.
 */
class MenuScreen(val game: SubmarinistGame) : Screen {

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(800f, 600f, camera)
    private val font = BitmapFont()
    private val background = Texture("background.png")

    init {
        font.data.setScale(2f)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        viewport.apply()
        game.batch.projectionMatrix = camera.combined

        game.batch.begin()
        // Dibujamos el fondo estirado
        game.batch.draw(background, 0f, 0f, 800f, 600f)
        
        font.draw(game.batch, "SUBMARINIST GAME", 0f, 400f, 800f, Align.center, false)
        font.draw(game.batch, "Toca la pantalla para empezar", 0f, 250f, 800f, Align.center, false)
        game.batch.end()

        if (Gdx.input.isTouched) {
            game.screen = GameScreen(game)
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
        background.dispose()
    }
}
