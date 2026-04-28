package cat.xtec.ioc.utils

import java.util.Random

object Methods {
    // Mètode que torna un float aleatòri entre un mínim i un màxim
    fun randomFloat(min: Float, max: Float): Float {
        val r = Random()
        return r.nextFloat() * (max - min) + min
    }
}
