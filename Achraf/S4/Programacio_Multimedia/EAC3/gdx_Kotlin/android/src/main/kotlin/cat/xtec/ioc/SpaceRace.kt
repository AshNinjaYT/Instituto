package cat.xtec.ioc

import cat.xtec.ioc.helpers.AssetManager
import cat.xtec.ioc.helpers.AssetManager.load
import cat.xtec.ioc.screens.SplashScreen
import com.badlogic.gdx.Game

class SpaceRace : Game() {
    override fun create() {
        System.err.println("PUNT 2")
        // A l'iniciar el joc carreguem els recursos
        load()
        // I definim la pantalla d'splash com a pantalla
        setScreen(SplashScreen(this))
    }

    // Cridem per descartar els recursos carregats.
    override fun dispose() {
        super.dispose()
        AssetManager.dispose()
    }
}
