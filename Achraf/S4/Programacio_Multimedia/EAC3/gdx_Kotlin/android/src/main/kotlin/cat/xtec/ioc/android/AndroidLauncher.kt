package cat.xtec.ioc.android

import android.os.Bundle
import cat.xtec.ioc.SpaceRace

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration


/** Launches the Android application. */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(SpaceRace(), AndroidApplicationConfiguration().apply {
            //Application configuration
            useAccelerometer = false;
            useCompass = false;
            // Impedim que s'apagui la pantalla
            useWakelock = true;
            // Posem el mode immersive per ocultar botons software
            useImmersiveMode = true
        })
    }
}
