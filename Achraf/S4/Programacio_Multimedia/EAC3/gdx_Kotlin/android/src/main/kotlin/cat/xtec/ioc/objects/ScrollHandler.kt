package cat.xtec.ioc.objects

import cat.xtec.ioc.utils.Methods
import cat.xtec.ioc.utils.Settings
import com.badlogic.gdx.scenes.scene2d.Group
import java.util.Random

class ScrollHandler : Group() {
    // Fons de pantalla

    // Creem els dos fons
    var bg: Background = Background(
        0f,
        0f,
        (Settings.GAME_WIDTH * 2).toFloat(),
        Settings.GAME_HEIGHT.toFloat(),
        Settings.BG_SPEED.toFloat()
    )
    var bg_back: Background = Background(
        bg.tailX,
        0f,
        (Settings.GAME_WIDTH * 2).toFloat(),
        Settings.GAME_HEIGHT.toFloat(),
        Settings.BG_SPEED.toFloat()
    )

    // Asteroides
    var numAsteroids: Int
    @JvmField
    val asteroids: ArrayList<Asteroid>

    // Objecte Random
    var r: Random


    init {
        // Afegim els fons al grup
        addActor(bg)
        addActor(bg_back)

        // Creem l'objecte random
        r = Random()

        // Comencem amb 3 asteroids
        numAsteroids = 3

        // Creem l'ArrayList
        asteroids = ArrayList()

        // Definim una mida aleatòria entre el mínim i el màxim
        var newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID) * 34

        // Afegim el primer Asteroid a l'Array i al grup
        var asteroid = Asteroid(
            Settings.GAME_WIDTH.toFloat(),
            r.nextInt(Settings.GAME_HEIGHT - newSize.toInt()).toFloat(),
            newSize,
            newSize,
            Settings.ASTEROID_SPEED.toFloat()
        )
        asteroids.add(asteroid)
        addActor(asteroid)

        // Des del segon fins l'últim asteroide
        for (i in 1 until numAsteroids) {
            // Creem la mida al·leatòria
            newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID) * 34
            // Afegim l'asteroid.
            asteroid = Asteroid(
                asteroids[asteroids.size - 1].tailX + Settings.ASTEROID_GAP, r.nextInt(
                    Settings.GAME_HEIGHT - newSize.toInt()
                ).toFloat(), newSize, newSize, Settings.ASTEROID_SPEED.toFloat()
            )
            // Afegim l'asteroide a l'ArrayList
            asteroids.add(asteroid)
            // Afegim l'asteroide al grup d'actors
            addActor(asteroid)
        }
    }

    override fun act(delta: Float) {
        super.act(delta)
        // Si algun element està fora de la pantalla, fem un reset de l'element.
        if (bg.isLeftOfScreen) {
            bg.reset(bg_back.tailX)
        } else if (bg_back.isLeftOfScreen) {
            bg_back.reset(bg.tailX)
        }

        for (i in asteroids.indices) {
            val asteroid = asteroids[i]
            if (asteroid.isLeftOfScreen) {
                if (i == 0) {
                    asteroid.reset(asteroids[asteroids.size - 1].tailX + Settings.ASTEROID_GAP)
                } else {
                    asteroid.reset(asteroids[i - 1].tailX + Settings.ASTEROID_GAP)
                }
            }
        }
    }

    fun collides(nau: Spacecraft): Boolean {
        // Comprovem les col·lisions entre cada asteroid i la nau

        for (asteroid in asteroids) {
            if (asteroid.collides(nau)) {
                return true
            }
        }
        return false
    }

    fun reset() {
        // Posem el primer asteroid fora de la pantalla per la dreta

        asteroids[0].reset(Settings.GAME_WIDTH.toFloat())
        // Calculem les noves posicions de la resta d'asteroids.
        for (i in 1 until asteroids.size) {
            asteroids[i].reset(asteroids[i - 1].tailX + Settings.ASTEROID_GAP)
        }
    }
}
