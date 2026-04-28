package cat.xtec.ioc.utils

object Settings {
    // Mida del joc, s'escalarà segons la necessitat
    const val GAME_WIDTH: Int = 240
    const val GAME_HEIGHT: Int = 135

    // Propietats de la nau
    const val SPACECRAFT_VELOCITY: Float = 75f
    const val SPACECRAFT_WIDTH: Int = 36
    const val SPACECRAFT_HEIGHT: Int = 15
    const val SPACECRAFT_STARTX: Float = 20f
    const val SPACECRAFT_STARTY: Float = (GAME_HEIGHT / 2 - SPACECRAFT_HEIGHT / 2).toFloat()

    // Rang de valors per canviar la mida de l'asteroide.
    const val MAX_ASTEROID: Float = 1.5f
    const val MIN_ASTEROID: Float = 0.5f

    // Configuració Scrollable
    const val ASTEROID_SPEED: Int = -150
    const val ASTEROID_GAP: Int = 75
    const val BG_SPEED: Int = -100

    // TODO Exercici 2: Propietats per la moneda
    const val SCORE_INCREASE: Int = 100 // s'incrementa en 100 cada cop que toca una moneda
    const val SCORE_SPEED: Int = -175
}
