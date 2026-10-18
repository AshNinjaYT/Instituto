package com.achraf.submarinista.android

import android.os.Bundle

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.achraf.submarinista.SubmarinistGame

/** Launches the Android application. */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(SubmarinistGame(), AndroidApplicationConfiguration().apply {
            // Desactivamos sensores que no usaremos
            useAccelerometer = false
            useCompass = false
            // Evitamos que se apague la pantalla
            useWakelock = true
            // Ocultamos botones de navegación
            useImmersiveMode = true
        })
    }
}
