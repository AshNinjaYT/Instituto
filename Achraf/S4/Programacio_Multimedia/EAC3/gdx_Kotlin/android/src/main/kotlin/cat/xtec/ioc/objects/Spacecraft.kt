package cat.xtec.ioc.objects

import cat.xtec.ioc.helpers.AssetManager
import cat.xtec.ioc.utils.Settings
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable

class Spacecraft(x: Float, y: Float, private val width: Int, private val height: Int) : Actor() {
    // Paràmetres de la spacecraft

    // Inicialitzem els arguments segons la crida del constructor
    private val position = Vector2(x, y)
    private var direction: Int

    var collisionRect: Rectangle
        private set


    init {
        // Inicialitzem la spacecraft a l'estat normal
        direction = SPACECRAFT_STRAIGHT

        // Creem el rectangle de col·lisions
        collisionRect = Rectangle()

        // Per a la gestio de hit
        setBounds(position.x, position.y, width.toFloat(), height.toFloat())
        touchable = Touchable.enabled
    }


    override fun act(delta: Float) {
        super.act(delta)

        when (direction) {
            SPACECRAFT_UP -> if (position.y - Settings.SPACECRAFT_VELOCITY * delta >= 0) {
                position.y -= Settings.SPACECRAFT_VELOCITY * delta
            }

            SPACECRAFT_DOWN -> if (position.y + height + (Settings.SPACECRAFT_VELOCITY * delta) <= Settings.GAME_HEIGHT) {
                position.y += Settings.SPACECRAFT_VELOCITY * delta
            }

            SPACECRAFT_STRAIGHT -> {}
        }
        collisionRect[position.x, position.y + 3, width.toFloat()] = 10f
        setBounds(position.x, position.y, width.toFloat(), height.toFloat())
    }

    // Getters dels atributs principals
    override fun getX(): Float {
        return position.x
    }

    override fun getY(): Float {
        return position.y
    }

    override fun getWidth(): Float {
        return width.toFloat()
    }

    override fun getHeight(): Float {
        return height.toFloat()
    }

    // Canviem la direcció de la spacecraft: Puja
    fun goUp() {
        direction = SPACECRAFT_UP
    }

    // Canviem la direcció de la spacecraft: Baixa
    fun goDown() {
        direction = SPACECRAFT_DOWN
    }

    // Posem la spacecraft al seu estat original
    fun goStraight() {
        direction = SPACECRAFT_STRAIGHT
    }

    val spacecraftTexture: TextureRegion?
        // Obtenim el TextureRegion depenent de la posició de la spacecraft
        get() = when (direction) {
            SPACECRAFT_STRAIGHT -> AssetManager.spacecraft
            SPACECRAFT_UP -> AssetManager.spacecraftUp
            SPACECRAFT_DOWN -> AssetManager.spacecraftDown
            else -> AssetManager.spacecraft
        }

    fun reset() {
        // La posem a la posició inicial i a l'estat normal

        position.x = Settings.SPACECRAFT_STARTX
        position.y = Settings.SPACECRAFT_STARTY
        direction = SPACECRAFT_STRAIGHT
        collisionRect = Rectangle()
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        batch.draw(spacecraftTexture, position.x, position.y, width.toFloat(), height.toFloat())
    }

    companion object {
        // Distintes posicions de la spacecraft, recta, pujant i baixant
        const val SPACECRAFT_STRAIGHT: Int = 0
        const val SPACECRAFT_UP: Int = 1
        const val SPACECRAFT_DOWN: Int = 2
    }
}
