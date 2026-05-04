package com.achraf.submarinista

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class Player(startX:Float,startY:Float) {
    var position = Vector2(startX, startY)
    var velocity = Vector2(0f, 0f)
    val size = 48f    
    val gravity = 250f

    fun update(delta: Float){

        velocity.y -= gravity * delta
        position.x += velocity.x * delta
        position.y += velocity.y * delta 
    }
}