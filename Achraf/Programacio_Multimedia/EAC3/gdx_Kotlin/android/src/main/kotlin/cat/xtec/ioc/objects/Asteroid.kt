package cat.xtec.ioc.objects

import cat.xtec.ioc.helpers.AssetManager
import cat.xtec.ioc.utils.Methods
import cat.xtec.ioc.utils.Settings
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction
import java.util.Random


class Asteroid(x: Float, y: Float,  width: Float, height: Float, velocity: Float) : Scrollable(x, y, width, height, velocity) {

    private val collisionCircle: Circle = Circle()
    private val r: Random = Random()
    private var assetAsteroid: Int = r.nextInt(15)

    init {
        setOrigin()

        // Rotación
        val rotateAction = RotateByAction().apply {
            amount = -90f
            duration = 0.2f
        }

        // Acción de repetición
        val repeat = RepeatAction().apply {
            action = rotateAction
            count = RepeatAction.FOREVER
        }

        // Equivalente:
        // this.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.rotateBy(-90f, 0.2f)));

        this.addAction(repeat)
    }

    fun setOrigin() {
        this.setOrigin(width / 2 + 1, height / 2)
    }

    override fun act(delta: Float) {
        super.act(delta)
        // Actualizamos el círculo de colisiones (punto central del asteroide y el radio).
        collisionCircle.set(position.x + width / 2.0f, position.y + width / 2.0f, width / 2.0f)
    }

    override fun reset(newX: Float) {
        super.reset(newX)
        // Obtenemos un número aleatorio entre MIN y MAX
        val newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID)
        // Modificamos la altura y el ancho según el aleatorio anterior
        width = 34 * newSize
        height = 34 * newSize
        // La posición será un valor aleatorio entre 0 y la altura de la aplicación menos la altura
        position.y = Random().nextInt(Settings.GAME_HEIGHT - height.toInt()).toFloat()

        assetAsteroid = r.nextInt(15)
        setOrigin()
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        batch.draw(AssetManager.asteroid[assetAsteroid], position.x, position.y, this.originX, this.originY, width, height, this.scaleX, this.scaleY, this.rotation)
    }

    // Retorna true si hay colisión
    fun collides(nau: Spacecraft): Boolean {
        if (position.x <= nau.x + nau.width) {
            // Comprobamos si han colisionado siempre y cuando el asteroide esté a la misma altura que la nave espacial
            return Intersector.overlaps(collisionCircle, nau.collisionRect)
        }
        return false
    }
}
