package cat.xtec.ioc.screens

import cat.xtec.ioc.SpaceRace
import cat.xtec.ioc.helpers.AssetManager
import cat.xtec.ioc.utils.Settings
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction
import com.badlogic.gdx.scenes.scene2d.ui.Container
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.utils.viewport.StretchViewport

class SplashScreen(private val game: SpaceRace) : Screen {
    private val stage: Stage

    private val textStyle: LabelStyle
    private val textLbl: Label


    init {
        // Creem la càmera de les dimensions del joc
        val camera =
            OrthographicCamera(Settings.GAME_WIDTH.toFloat(), Settings.GAME_HEIGHT.toFloat())
        // Posant el paràmetre a true configurem la càmera per a
        // que faci servir el sistema de coordenades Y-Down
        camera.setToOrtho(true)

        // Creem el viewport amb les mateixes dimensions que la càmera
        val viewport =
            StretchViewport(Settings.GAME_WIDTH.toFloat(), Settings.GAME_HEIGHT.toFloat(), camera)

        // Creem l'stage i assginem el viewport
        stage = Stage(viewport)

        // Afegim el fons
        stage.addActor(Image(AssetManager.background))

        // Creem l'estil de l'etiqueta i l'etiqueta
        textStyle = LabelStyle(AssetManager.font, null)
        textLbl = Label("SpaceRace", textStyle)

        // Creem el contenidor necessari per aplicar-li les accions
        val container: Container<*> = Container<Actor>(textLbl)
        container.isTransform = true
        container.center()
        container.setPosition(
            (Settings.GAME_WIDTH / 2).toFloat(),
            (Settings.GAME_HEIGHT / 2).toFloat()
        )

        // Afegim les accions de escalar: primer es fa gran i després torna a l'estat original ininterrompudament
        container.addAction(
            Actions.repeat(
                RepeatAction.FOREVER,
                Actions.sequence(Actions.scaleTo(1.5f, 1.5f, 1f), Actions.scaleTo(1f, 1f, 1f))
            )
        )
        stage.addActor(container)

        // Creem la imatge de la nau i li assignem el moviment en horitzontal
        val spacecraft = Image(AssetManager.spacecraft)
        val y = Settings.GAME_HEIGHT / 2 + textLbl.height
        spacecraft.addAction(
            Actions.repeat(
                RepeatAction.FOREVER, Actions.sequence(
                    Actions.moveTo(0 - spacecraft.width, y), Actions.moveTo(
                        Settings.GAME_WIDTH.toFloat(), y, 5f
                    )
                )
            )
        )

        stage.addActor(spacecraft)
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        stage.draw()
        stage.act(delta)

        // Si es fa clic en la pantalla, canviem la pantalla
        if (Gdx.input.isTouched) {
            game.screen = GameScreen(stage.batch, stage.viewport)
            dispose()
        }
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
