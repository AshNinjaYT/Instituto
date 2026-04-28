package cat.xtec.ioc.helpers

import cat.xtec.ioc.objects.Spacecraft
import cat.xtec.ioc.screens.GameScreen
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import kotlin.math.abs

class InputHandler(private val screen: GameScreen) : InputProcessor {
    // Enter per a la gesitó del moviment d'arrastrar
    var previousY: Int = 0
    // Objectes necessaris

    // Obtenim tots els elements necessaris
    private val spacecraft: Spacecraft = screen.spacecraft
    private var stageCoord: Vector2? = null

    private val stage: Stage = screen.stage

    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        when (screen.currentState) {
            GameScreen.GameState.READY ->                 // Si fem clic comencem el joc
                screen.currentState = GameScreen.GameState.RUNNING

            GameScreen.GameState.RUNNING -> {
                previousY = screenY

                stageCoord =
                    stage.screenToStageCoordinates(Vector2(screenX.toFloat(), screenY.toFloat()))
                val actorHit = stage.hit(stageCoord!!.x, stageCoord!!.y, true)
                if (actorHit != null) {
                    Gdx.app.log("HIT", actorHit.name)
                }
            }

            GameScreen.GameState.GAMEOVER -> screen.reset()
        }
        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        // Quan deixem anar el dit acabem un moviment
        // i posem la nau en l'estat normal

        spacecraft.goStraight()
        return true
    }

    override fun touchCancelled(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }


    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        // Posem un umbral per evitar gestionar events quan el dit està quiet
        if (abs((previousY - screenY).toDouble()) > 2) // Si la Y és major que la que tenim
        // guardada és que va cap avall
            if (previousY < screenY) {
                spacecraft.goDown()
            } else {
                // En cas contrari cap a dalt
                spacecraft.goUp()
            }
        // Guardem la posició de la Y
        previousY = screenY
        return true
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        return false
    }
}
