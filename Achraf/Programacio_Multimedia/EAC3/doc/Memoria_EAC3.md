# MEMORIA EAC3 - SUBMARINIST GAME
**Alumno:** Achraf
**Fecha:** 11/05/2026

---

## 2) Ejercicio práctico (8 Puntos)

### Apartado 1: Movimiento básico del submarinista y cámara

**1. Movimiento del personaje**
Implementa el movimiento básico del submarinista dentro del mapa. El movimiento debe simular flotación bajo el agua: cuando el jugador impulsa al personaje, este sube, cuando no lo impulsa, baja lentamente. El personaje también debe poder desplazarse lateralmente por el mapa. El mapa debe ser más grande que la pantalla del dispositivo.

Apartado Hecho? [x]

**Código:**
```kotlin
// Lógica de movimiento en Player.kt (dentro del método update)
fun update(delta: Float, isTouched: Boolean, isUpPressed: Boolean) {
    stateTime += delta
    
    // Control vertical (impulso vs gravedad)
    if (isTouched || isUpPressed) {
        velocity.y += 700f * delta
        if (velocity.y > 250f) velocity.y = 250f
        state = State.UP
    } else {
        velocity.y -= gravity * delta
        if (velocity.y < -200f) velocity.y = -200f
        state = if (velocity.y < -20f) State.DOWN else State.IDLE
    }

    // Aplicar movimiento y fricción horizontal
    position.x += velocity.x * delta
    position.y += velocity.y * delta
    velocity.x *= 0.9f
}
```
**Captura:**
![Captura Movimiento](img/captura_1_1.png)

---

**2. Cámara que sigue al personaje**
Haz que la cámara siga al submarinista mientras se desplaza por el mapa. El personaje debe mantenerse dentro de la vista de la cámara. Ajusta los límites de la cámara para que no muestre zonas fuera del mapa.

Apartado Hecho? [x]

**Código:**
```kotlin
// Seguimiento y límites de cámara en GameScreen.kt (método render)
camera.position.set(player.position.x, player.position.y, 0f)

// Cálculo de límites según el tamaño del mapa cargado
val mapaAnchoPixeles = mapLayer.width * mapLayer.tileWidth
val mapaAltoPixeles = mapLayer.height * mapLayer.tileHeight

// Restricción de la cámara para no mostrar el vacío exterior
camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2f, mapaAnchoPixeles - camera.viewportWidth / 2f)
camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2f, mapaAltoPixeles - camera.viewportHeight / 2f)
camera.update()
```
**Captura:**
![Captura Cámara](img/captura_1_2.png)

---

**3. Paredes y colisiones con el entorno**
Añade paredes, rocas, techo, suelo u otros límites al mapa. El personaje no debe poder atravesar estos elementos. Es necesario implementar colisiones con el entorno.

Apartado Hecho? [x]

**Código:**
```kotlin
// Detección de colisiones por Tiles en GameScreen.kt
private fun comprobarColisionesGenerico(p: Player): Boolean {
    val playerRect = p.getBoundingRectangle()
    val startX = (p.position.x / 32).toInt()
    val startY = (p.position.y / 32).toInt()

    // Comprobamos los tiles en una rejilla de 2x2 alrededor del jugador
    for (x in startX..startX + 1) {
        for (y in startY..startY + 1) {
            val c1 = mapLayer.getCell(x, y)
            val c2 = mapLayer2?.getCell(x, y)
            if ((c1 != null && c1.tile != null) || (c2 != null && c2.tile != null)) {
                rectBloque.set(x * 32f, y * 32f, 32f, 32f)
                if (playerRect.overlaps(rectBloque)) return true
            }
        }
    }
    return false
}
```
**Captura:**
![Captura Colisiones](img/captura_1_3.png)

---

### Apartado 2: Animaciones del personaje

**1. Animaciones del submarinista**
Implementa animaciones del personaje según su estado de movimiento. Como mínimo, hay que mostrar animaciones diferenciadas para: subida, descenso, desplazamiento lateral o reposo.

Apartado Hecho? [x]

**Código:**
```kotlin
// Gestión de frames y dibujo en GameScreen.kt
val currentAnim = if (player.state == Player.State.UP || player.velocity.x != 0f) animSwimming else animIdle
val currentFrame = currentAnim.getKeyFrame(player.stateTime)

game.batch.begin()
// Invertir el sprite según la dirección horizontal
if ((player.velocity.x < 0) != currentFrame.isFlipX) {
    currentFrame.flip(true, false)
}
game.batch.draw(currentFrame, player.position.x, player.position.y, player.size, player.size)
game.batch.end()
```
**Captura:**
![Captura Animaciones](img/captura_2_1.png)

---

### Apartado 3: Recogida de objetos

**1. Tipos de objetos**
Añade dos tipos de objetos dispersos por el mapa: Tesoros (aumentan puntos) y Burbujas de oxígeno (aumentan el oxígeno).

Apartado Hecho? [x]

**Captura:**
![Captura Tipos Objetos](img/captura_3_1.png)

---

**2. Implementación de colisiones con objetos**
Implementa la colisión entre el submarinista y los objetos. Cuando el personaje toca un objeto, este debe recogerse y desaparecer, y se debe modificar la puntuación o el oxígeno.

Apartado Hecho? [x]

**Código:**
```kotlin
// Gestión de la lista de objetos con Iterator en GameScreen.kt
val itemIter = items.iterator()
while (itemIter.hasNext()) {
    val item = itemIter.next()
    game.batch.draw(item.region, item.position.x, item.position.y, item.size, item.size)
    
    // Comprobar solapamiento usando Intersector
    if (com.badlogic.gdx.math.Intersector.overlaps(player.getBoundingRectangle(), item.getBoundingRectangle())) {
        if (item.type == ItemType.TREASURE) {
            player.score += 1
            player.treasuresCollected++
        } else {
            player.oxygen = Math.min(100f, player.oxygen + 20f)
        }
        itemIter.remove() // El objeto desaparece del mapa
    }
}
```
**Captura:**
![Captura Colisión Objetos](img/captura_3_2.png)

---

### Apartado 4: Minas submarinas y detección de proximidad

**1. Minas en movimiento**
Añade minas submarinas que se muevan de manera repetitiva arriba y abajo entre dos puntos de manera automática.

Apartado Hecho? [x]

**Código:**
```kotlin
// Lógica de oscilación en Mina.kt
fun update(delta: Float) {
    stateTime += delta
    if (state == MinaState.ACTIVE) {
        // Movimiento sinusoidal automático amunt i avall
        position.y = startPosition.y + MathUtils.sin(stateTime * speed + offset) * range
    } else if (state == MinaState.EXPLODING) {
        if (explosionAnim.isAnimationFinished(stateTime - explosionStartTime)) {
            state = MinaState.REMOVED
        }
    }
}
```
**Captura:**
![Captura Minas Movimiento](img/captura_4_1.png)

---

**2. Activación por proximidad**
Implementa un sistema para que la mina detecte si el jugador se acerca demasiado. Cuando el submarinista entra dentro de una cierta distancia, la mina debe activarse o explotar.

Apartado Hecho? [x]

**Código:**
```kotlin
// Detección de proximidad en GameScreen.kt
val dist = Vector2.dst(player.position.x + player.size/2, player.position.y + player.size/2, 
                       mina.position.x + mina.size/2, mina.position.y + mina.size/2)

if (mina.state == MinaState.ACTIVE && dist < 60f) {
    mina.state = MinaState.EXPLODING
    mina.explosionStartTime = mina.stateTime
    player.oxygen -= 30f // Impacto inmediato al entrar en el radio
}
```
**Captura:**
![Captura Proximidad](img/captura_4_2.png)

---

**3. Explosión y radio de daño**
Cuando una mina explota, debe hacer daño si el submarinista se encuentra dentro del radio de la explosión.

Apartado Hecho? [x]

**Código:**
```kotlin
// Representación visual de la explosión y daño radial
if (mina.state == MinaState.EXPLODING) {
    val explosionFrame = mina.explosionAnim.getKeyFrame(mina.stateTime - mina.explosionStartTime)
    game.batch.draw(explosionFrame, mina.position.x - 16f, mina.position.y - 16f, mina.size + 32f, mina.size + 32f)
}
```
**Captura:**
![Captura Explosión](img/captura_4_3.png)

---

### Apartado 5: Oxígeno, daño e interfaz

**1. Pérdida de oxígeno**
El submarinista debe perder oxígeno de manera progresiva con el paso del tiempo y cuando recibe daño.

Apartado Hecho? [x]

**Código:**
```kotlin
// Desgaste de oxígeno en Player.kt (método update)
oxygen -= 2f * delta // Descenso constante por respiración
if (oxygen < 0f) oxygen = 0f
```
**Captura:**
![Captura Pérdida Oxígeno](img/captura_5_1.png)

---

**2. Interfaz de juego**
Muestra en pantalla el oxígeno actual y la puntuación del jugador.

Apartado Hecho? [x]

**Código:**
```kotlin
// Dibujado del HUD con cámara fija en GameScreen.kt
private fun drawHUD() {
    game.batch.projectionMatrix = hudCamera.combined
    game.batch.begin()
    // ... dibujo de fondo ...
    drawTextWithShadowHUD("PUNTOS: ${player.score}", 32f, hudViewport.worldHeight - 15f, colorTextoHUD)
    
    val oxyColor = if (player.oxygen > 30) colorOxigenoOk else colorOxigenoCritico
    drawTextWithShadowHUD("OXÍGENO: ${player.oxygen.toInt()}%", hudViewport.worldWidth - 250f, hudViewport.worldHeight - 15f, oxyColor)
    game.batch.end()
}
```
**Captura:**
![Captura Interfaz](img/captura_5_2.png)

---

### Apartado 6: Final de partida

**1. Derrota cuando se acaba el oxígeno**
El juego debe terminar cuando el oxígeno llega a cero.

Apartado Hecho? [x]

**Código:**
```kotlin
// Cambio de pantalla por muerte en GameScreen.kt
if (player.oxygen <= 0) {
    game.screen = GameOverScreen(game, player.score, player.timeSurvived, player.treasuresCollected)
    return
}
```
**Captura:**
![Captura Derrota](img/captura_6_1.png)

---

**2. Pantalla o mensaje de final de partida**
Muestra un mensaje o pantalla que indique claramente que el jugador ha perdido por falta de oxígeno.

Apartado Hecho? [x]

**Captura:**
![Captura Pantalla Final](img/captura_6_2.png)

---

### Apartado 7: Pantalla de resultados del juego

**1. Resultados finales**
Añade una pantalla de resultados que muestre la puntuación final, el tiempo total que ha sobrevivido y el nombre de tesoros recogidos. La victoria se activa dinámicamente al recoger todas las monedas del mapa.

Apartado Hecho? [x]

**Código:**
```kotlin
c
```
**Captura:**
![Captura Resultados Finales](img/captura_7_1.png)
