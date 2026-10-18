package cat.xtec.ioc.screens

import cat.xtec.ioc.helpers.AssetManager
import cat.xtec.ioc.helpers.InputHandler
import cat.xtec.ioc.objects.Asteroid
import cat.xtec.ioc.objects.ScrollHandler
import cat.xtec.ioc.objects.Spacecraft
import cat.xtec.ioc.utils.Settings
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport

class GameScreen(prevBatch: Batch?, prevViewport: Viewport?) : Screen {
    // Els estats del joc
    enum class GameState {
        READY, RUNNING, GAMEOVER
    }

    var currentState: GameState

    // Objectes necessaris
    val stage: Stage
    val spacecraft: Spacecraft
    val scrollHandler: ScrollHandler

    // Encarregats de dibuixar elements per pantalla
    private val shapeRenderer: ShapeRenderer
    private val batch: Batch

    // Per controlar l'animació de l'explosió
    private var explosionTime = 0f

    // Preparem el textLayout per escriure text
    private val textLayout: GlyphLayout


    init {
        // Iniciem la música

        AssetManager.music!!.play()

        // Creem el ShapeRenderer
        shapeRenderer = ShapeRenderer()

        // Creem l'stage i assginem el viewport
        stage = Stage(prevViewport, prevBatch)

        batch = stage.batch

        // Creem la nau i la resta d'objectes
        spacecraft = Spacecraft(
            Settings.SPACECRAFT_STARTX,
            Settings.SPACECRAFT_STARTY,
            Settings.SPACECRAFT_WIDTH,
            Settings.SPACECRAFT_HEIGHT
        )
        scrollHandler = ScrollHandler()

        // Afegim els actors a l'stage
        stage.addActor(scrollHandler)
        stage.addActor(spacecraft)
        // Donem nom a l'Actor
        spacecraft.name = "spacecraft"

        // Iniciem el GlyphLayout
        textLayout = GlyphLayout()
        textLayout.setText(AssetManager.font, "Are you\nready?")

        currentState = GameState.READY

        // Assignem com a gestor d'entrada la classe InputHandler
        Gdx.input.inputProcessor = InputHandler(this)
    }

    private fun drawElements() {
        // Recollim les propietats del Batch de l'Stage

        shapeRenderer.projectionMatrix = batch.projectionMatrix

        // Pintem el fons de negre per evitar el "flickering"
        //Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        //Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Inicialitzem el shaperenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)

        // Definim el color (verd)
        shapeRenderer.color = Color(0f, 1f, 0f, 1f)

        // Pintem la nau
        shapeRenderer.rect(spacecraft.x, spacecraft.y, spacecraft.width, spacecraft.height)

        // Recollim tots els Asteroid
        val asteroids = scrollHandler.asteroids
        var asteroid: Asteroid

        for (i in asteroids.indices) {
            asteroid = asteroids[i]
            when (i) {
                0 -> shapeRenderer.setColor(1f, 0f, 0f, 1f)
                1 -> shapeRenderer.setColor(0f, 0f, 1f, 1f)
                2 -> shapeRenderer.setColor(1f, 1f, 0f, 1f)
                else -> shapeRenderer.setColor(1f, 1f, 1f, 1f)
            }
            shapeRenderer.circle(
                asteroid.x + asteroid.width / 2,
                asteroid.y + asteroid.width / 2,
                asteroid.width / 2
            )
        }
        shapeRenderer.end()
    }


    override fun show() {
    }

    override fun render(delta: Float) {
        // Dibuixem tots els actors de l'stage

        stage.draw()

        when (currentState) {
            GameState.GAMEOVER -> updateGameOver(delta)
            GameState.RUNNING -> updateRunning(delta)
            GameState.READY -> updateReady()
        }        //drawElements();
    }

    private fun updateReady() {
        // Dibuixem el text al centre de la pantalla

        batch.begin()
        AssetManager.font!!.draw(
            batch,
            textLayout,
            (Settings.GAME_WIDTH / 2) - textLayout.width / 2,
            (Settings.GAME_HEIGHT / 2) - textLayout.height / 2
        )
        //stage.addActor(textLbl);
        batch.end()
    }

    private fun updateRunning(delta: Float) {
        stage.act(delta)

        if (scrollHandler.collides(spacecraft)) {
            // Si hi ha hagut col·lisió: Reproduïm l'explosió i posem l'estat a GameOver
            AssetManager.explosionSound!!.play()
            stage.root.findActor<Actor>("spacecraft").remove()
            textLayout.setText(AssetManager.font, "Game Over :'(")
            currentState = GameState.GAMEOVER
        }
    }

    private fun updateGameOver(delta: Float) {
        stage.act(delta)

        batch.begin()
        AssetManager.font!!.draw(
            batch,
            textLayout,
            (Settings.GAME_WIDTH - textLayout.width) / 2,
            (Settings.GAME_HEIGHT - textLayout.height) / 2
        )
        // Si hi ha hagut col·lisió: Reproduïm l'explosió i posem l'estat a GameOver
        batch.draw(
            AssetManager.explosionAnim!!.getKeyFrame(explosionTime, false) as TextureRegion,
            (spacecraft.x + spacecraft.width / 2) - 32,
            spacecraft.y + spacecraft.height / 2 - 32,
            64f,
            64f
        )
        batch.end()

        explosionTime += delta
    }

    fun reset() {
        // Posem el text d'inici

        textLayout.setText(AssetManager.font, "Are you\nready?")
        // Cridem als restart dels elements.
        spacecraft.reset()
        scrollHandler.reset()

        // Posem l'estat a 'Ready'
        currentState = GameState.READY

        // Afegim la nau a l'stage
        stage.addActor(spacecraft)

        // Posem a 0 les variables per controlar el temps jugat i l'animació de l'explosió
        explosionTime = 0.0f
    }


    override fun resize(width: Int, height: Int) {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
    }
}
