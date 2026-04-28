package cat.xtec.ioc.helpers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import cat.xtec.ioc.helpers.AssetManager.music as music1

object AssetManager {
    // Sprite Sheet
    var sheet: Texture? = null

    // Nau i fons
    @JvmField
    var spacecraft: TextureRegion? = null
    @JvmField
    var spacecraftDown: TextureRegion? = null
    @JvmField
    var spacecraftUp: TextureRegion? = null
    @JvmField
    var background: TextureRegion? = null

    // Asteroid
    lateinit var asteroid: Array<TextureRegion?>
    var asteroidAnim: Animation<*>? = null

    // Explosió
    lateinit var explosion: Array<TextureRegion?>
    @JvmField
    var explosionAnim: Animation<*>? = null

    // Sons
    @JvmField
    var explosionSound: Sound? = null
    @JvmField
    var music: Music? = null


    // Font
    @JvmField
    var font: BitmapFont? = null


    @JvmStatic
    fun load() {
        // Carreguem les textures i li apliquem el mètode d'escalat 'nearest'
        sheet = Texture(Gdx.files.internal("sheet.png"))
        sheet!!.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)

        // Sprites de la nau
        spacecraft = TextureRegion(sheet, 0, 0, 36, 15)
        spacecraft!!.flip(false, true)

        spacecraftUp = TextureRegion(sheet, 36, 0, 36, 15)
        spacecraftUp!!.flip(false, true)

        spacecraftDown = TextureRegion(sheet, 72, 0, 36, 15)
        spacecraftDown!!.flip(false, true)


        // Carreguem els 16 estats de l'asteroid
        asteroid = arrayOfNulls(16)
        for (i in asteroid.indices) {
            asteroid[i] = TextureRegion(sheet, i * 34, 15, 34, 34)

            //  asteroid[i].flip(false, true);
        }


        // Creem l'animació de l'asteroid i fem que s'executi contínuament en sentit anti-horari
        asteroidAnim = Animation<Any?>(0.05f, *asteroid)
        (asteroidAnim as Animation<Any?>).setPlayMode(Animation.PlayMode.LOOP_REVERSED)

        // Creem els 16 estats de l'explosió
        explosion = arrayOfNulls(16)

        // Carreguem els 16 estats de l'explosió
        var index = 0
        for (i in 0..1) {
            for (j in 0..7) {
                explosion[index++] = TextureRegion(sheet, j * 64, i * 64 + 49, 64, 64)
                //  explosion[index-1].flip(false, true);
            }
        }

        // Finalment creem l'animació
        explosionAnim = Animation<Any?>(0.04f, *explosion)

        // Fons de pantalla
        background = TextureRegion(sheet, 0, 177, 480, 135)

        //    background.flip(false, true);
        /******************************* Sounds  */
        // Explosió
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"))

        // Música del joc
        music1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/Afterburner.ogg"))
        music1?.run {
            setVolume(0.2f)
            setLooping(true)
        }


        /******************************* Text  */
        // Font space
        val fontFile = Gdx.files.internal("fonts/space.fnt")
        font = BitmapFont(fontFile, true)
        font!!.data.setScale(0.4f)
    }


    @JvmStatic
    fun dispose() {
        // Alliberem els recursos gràfics i de audio

        sheet!!.dispose()
        explosionSound!!.dispose()
        music1!!.dispose()
    }
}
