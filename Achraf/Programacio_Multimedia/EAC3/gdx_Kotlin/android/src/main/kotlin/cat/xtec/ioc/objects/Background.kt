package cat.xtec.ioc.objects

import cat.xtec.ioc.helpers.AssetManager
import com.badlogic.gdx.graphics.g2d.Batch

class Background(x: Float, y: Float, width: Float, height: Float, velocity: Float) :
    Scrollable(x, y, width, height, velocity) {
    override fun draw(batch: Batch, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        batch.disableBlending()
        batch.draw(AssetManager.background, position.x, position.y, width, height)
        batch.enableBlending()
    }
}
