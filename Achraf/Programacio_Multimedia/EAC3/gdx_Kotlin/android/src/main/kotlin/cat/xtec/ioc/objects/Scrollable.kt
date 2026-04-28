package cat.xtec.ioc.objects

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor

open class Scrollable(
    x: Float,
    y: Float,
    private var width: Float,
    private var height: Float,
    protected var velocity: Float
) : Actor() {
    protected var position: Vector2 = Vector2(x, y)
    var isLeftOfScreen: Boolean
        protected set

    init {
        isLeftOfScreen = false
    }

    override fun act(delta: Float) {
        super.act(delta)
        // Desplacem l'objecte en l'eix de les x
        position.x += velocity * delta

        // Si està fora de la pantalla canviem la variable a true
        if (position.x + width < 0) {
            isLeftOfScreen = true
        }
    }

    open fun reset(newX: Float) {
        position.x = newX
        isLeftOfScreen = false
    }

    val tailX: Float
        get() = position.x + width

    override fun getX(): Float {
        return position.x
    }

    override fun getY(): Float {
        return position.y
    }

    override fun getWidth(): Float {
        return width
    }

    override fun getHeight(): Float {
        return height
    }
}
