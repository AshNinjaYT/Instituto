package com.achraf.submarinista

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport

// Esta es la pantalla principal donde jugamos
// Implemento Screen de LibGDX para poder cambiar entre menús y juego
class GameScreen(val game: SubmarinistGame) : Screen {

    // 1. VARIABLES BÁSICAS
    // La cámara nos permite ver una porción del mundo que sigue al jugador
    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var hudCamera: OrthographicCamera
    private lateinit var hudViewport: Viewport
    private lateinit var font: BitmapFont
    
    // Variables para el mapa de Tiled que he hecho
    private lateinit var map: TiledMap
    private lateinit var mapRenderer: OrthogonalTiledMapRenderer
    private lateinit var mapLayer: TiledMapTileLayer
    private lateinit var mapLayer2: TiledMapTileLayer
    private lateinit var obstaclesLayer: TiledMapTileLayer
    private val rectBloque = Rectangle() // Guardo esto aquí para no crear muchos objetos nuevos y que el juego vaya lento
    
    // Variables para los sprites del personaje
    private lateinit var idleSheet: Texture
    private lateinit var swimmingSheet: Texture
    private lateinit var backgroundTexture: Texture
    private lateinit var animIdle: com.badlogic.gdx.graphics.g2d.Animation<com.badlogic.gdx.graphics.g2d.TextureRegion>
    private lateinit var animSwimming: com.badlogic.gdx.graphics.g2d.Animation<com.badlogic.gdx.graphics.g2d.TextureRegion>
    
    // --- APARTADO 3: Cosas de los objetos recogibles ---
    private lateinit var treasureTexture: Texture
    private lateinit var oxygenTexture: Texture
    
    enum class ItemType { TREASURE, OXYGEN }
    
    inner class Item(val type: ItemType, val position: com.badlogic.gdx.math.Vector2, val region: com.badlogic.gdx.graphics.g2d.TextureRegion) {
        val size = 32f
        private val bounds = Rectangle(position.x, position.y, size, size)
        fun getBoundingRectangle() = bounds
    }
    
    // --- APARTADO 4: Las minas que flotan ---
    private lateinit var explosionTexture: Texture
    private lateinit var explosionAnim: com.badlogic.gdx.graphics.g2d.Animation<com.badlogic.gdx.graphics.g2d.TextureRegion>
    
    enum class MinaState { ACTIVE, EXPLODING, DONE }
    
    inner class Mina(val startPosition: Vector2, val region: com.badlogic.gdx.graphics.g2d.TextureRegion) {
        val position = Vector2(startPosition)
        val size = 45f // Basado en tu TMX
        var state = MinaState.ACTIVE
        var stateTime = 0f
        var explosionTime = 0f
        
        // Hago que se balanceen arriba y abajo
        val range = 60f
        val speed = MathUtils.random(1f, 2f)
        val offset = MathUtils.random(0f, 6.28f) // Para que empiecen en distinto sitio del balanceo

        fun update(delta: Float) {
            stateTime += delta
            if (state == MinaState.ACTIVE) {
                // Movimiento vertical suave (Apartat 4.1)
                position.y = startPosition.y + MathUtils.sin(stateTime * speed + offset) * range
            } else if (state == MinaState.EXPLODING) {
                explosionTime += delta
                if (explosionAnim.isAnimationFinished(explosionTime)) {
                    state = MinaState.DONE
                }
            }
        }
        
        fun getBoundingRectangle() = Rectangle(position.x, position.y, size, size)
    }

    // Listas nativas de LibGDX para almacenar los elementos
    private val items = Array<Item>()
    private val minas = Array<Mina>()
    
    // Nuestro objeto jugador que contiene la lógica de movimiento
    private lateinit var player: Player
    private lateinit var debugRenderer: com.badlogic.gdx.graphics.glutils.ShapeRenderer

    init {
        // --- INICIALIZACIÓN ---
        
        // Creamos la cámara. Le decimos que queremos ver un área de 800x600 píxeles.
        // Creamos la cámara. Zoom más cercano (400x300) para que se vea mejor el pixel art
        // Cámara y Viewport del juego (400x300 unidades de mundo)
        camera = OrthographicCamera()
        viewport = FitViewport(400f, 300f, camera)

        // Cámara y Viewport para el HUD (fijo en píxeles de pantalla)
        hudCamera = OrthographicCamera()
        hudViewport = FitViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat(), hudCamera)
        
        // Fuente para el texto
        font = BitmapFont()
        font.data.setScale(1.8f) // Un poco más grande para el HUD
        font.color = com.badlogic.gdx.graphics.Color.WHITE

        // Cargamos el mapa y el renderizador
        map = TmxMapLoader().load("map.tmx")
        mapRenderer = OrthogonalTiledMapRenderer(map)
        
        // Cargamos las capas sólidas buscando en la raíz o dentro de grupos
        mapLayer = getLayerByName(map, "Paredes") ?: throw IllegalArgumentException("No se encontró la capa Paredes")
        mapLayer2 = getLayerByName(map, "Paredes2") ?: mapLayer
        obstaclesLayer = getLayerByName(map, "Obstaculos") ?: mapLayer

        // --- CARGA DE ASSETS ---
        backgroundTexture = Texture("background.png")
        idleSheet = Texture("player-idle.png")
        swimmingSheet = Texture("player-swiming.png")
        
        // Dividimos las tiras de imágenes en fotogramas. 
        // Según hemos comprobado, estos assets son de 80x80 píxeles.
        // AÑADIMOS SEGURIDAD: Comprobamos el tamaño antes de cortar
        if (idleSheet.width >= 80 && idleSheet.height >= 80) {
            val idleFrames = com.badlogic.gdx.graphics.g2d.TextureRegion.split(idleSheet, 80, 80)[0]
            animIdle = com.badlogic.gdx.graphics.g2d.Animation(0.15f, *idleFrames)
        } else {
            // Si la imagen es pequeña, la usamos entera como un único frame
            animIdle = com.badlogic.gdx.graphics.g2d.Animation(0.15f, com.badlogic.gdx.graphics.g2d.TextureRegion(idleSheet))
        }

        if (swimmingSheet.width >= 80 && swimmingSheet.height >= 80) {
            val swimmingFrames = com.badlogic.gdx.graphics.g2d.TextureRegion.split(swimmingSheet, 80, 80)[0]
            animSwimming = com.badlogic.gdx.graphics.g2d.Animation(0.1f, *swimmingFrames)
        } else {
            animSwimming = com.badlogic.gdx.graphics.g2d.Animation(0.1f, com.badlogic.gdx.graphics.g2d.TextureRegion(swimmingSheet))
        }
        
        animIdle.playMode = com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP
        animSwimming.playMode = com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP

        // Cargamos las texturas de los objetos y explosión
        treasureTexture = Texture("treasure.png")
        oxygenTexture = Texture("oxygen.png")
        explosionTexture = Texture("explosion-small.png")
        
        // Setup de la animación de explosión (usamos el alto de la imagen como ancho para que los frames sean cuadrados)
        val expSize = explosionTexture.height
        val expFrames = com.badlogic.gdx.graphics.g2d.TextureRegion.split(explosionTexture, expSize, expSize)[0]
        explosionAnim = com.badlogic.gdx.graphics.g2d.Animation(0.08f, *expFrames)
        
        // CARGA DE OBJETOS DESDE TILED (Sustituye a spawnItems)
        val objectLayer = map.layers.get("Objetos")
        if (objectLayer != null) {
            for (obj in objectLayer.objects) {
                if (obj is com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject) {
                    val pos = Vector2(obj.x, obj.y)
                    val type = obj.properties.get("type", String::class.java)
                    val region = obj.tile.textureRegion
                    
                    when (type) {
                        "moneda" -> items.add(Item(ItemType.TREASURE, pos, region))
                        "oxigeno" -> items.add(Item(ItemType.OXYGEN, pos, region))
                        "mina" -> minas.add(Mina(pos, region))
                    }
                }
            }
        }

        // Inicializamos al jugador en una posición segura
        val spawnPos = findSafeSpawn()
        player = Player(spawnPos.x, spawnPos.y)

        // Debug renderer para ver colisiones
        debugRenderer = com.badlogic.gdx.graphics.glutils.ShapeRenderer()
    }

    /**
     * Busca el primer punto seguro (sin colisiones) al inicio del mapa.
     * Esto garantiza un spawn fijo pero siempre en una zona vacía.
     */
    private fun findSafeSpawn(): Vector2 {
        val tempPlayer = Player(0f, 0f)
        // Recorremos las primeras columnas y las filas centrales buscando un hueco de 64x64 libre
        for (tx in 3..20) {
            for (ty in 8..15) { 
                val posX = tx * mapLayer.tileWidth.toFloat()
                val posY = ty * mapLayer.tileHeight.toFloat()
                
                tempPlayer.position.set(posX, posY)
                
                // Usamos la lógica de colisión real para verificar el sitio
                if (!comprobarColisionesGenerico(tempPlayer)) {
                    println("Punto de inicio seguro detectado en: $posX, $posY")
                    return Vector2(posX, posY)
                }
            }
        }
        // Si no encontrara nada (improbable), usamos un punto central genérico
        return Vector2(300f, 300f)
    }

    /**
     * Busca una capa de tiles por nombre, incluso si está dentro de un "Grupo de capas" en Tiled.
     */
    private fun getLayerByName(map: TiledMap, name: String): TiledMapTileLayer? {
        for (layer in map.layers) {
            if (layer.name == name && layer is TiledMapTileLayer) {
                return layer
            }
            if (layer is com.badlogic.gdx.maps.MapGroupLayer) {
                for (child in layer.layers) {
                    if (child.name == name && child is TiledMapTileLayer) {
                        return child
                    }
                }
            }
        }
        return null
    }

    /**
     * Versión genérica de comprobarColisiones. 
     * Bloquea el paso si hay tiles en Paredes, Paredes2 u Obstaculos.
     */
    private fun comprobarColisionesGenerico(p: Player): Boolean {
        val playerRect = p.getBoundingRectangle()
        val startX = Math.max(0, (p.position.x / mapLayer.tileWidth).toInt() - 1)
        val startY = Math.max(0, (p.position.y / mapLayer.tileHeight).toInt() - 1)
        val endX = Math.min(mapLayer.width - 1, ((p.position.x + p.size) / mapLayer.tileWidth).toInt() + 1)
        val endY = Math.min(mapLayer.height - 1, ((p.position.y + p.size) / mapLayer.tileHeight).toInt() + 1)

        for (x in startX..endX) {
            for (y in startY..endY) {
                val c1 = mapLayer.getCell(x, y)
                val c2 = mapLayer2.getCell(x, y)
                val obs = obstaclesLayer.getCell(x, y)
                
                // Si hay un tile en cualquiera de las 3 capas sólidas
                if ((c1 != null && c1.tile != null) || 
                    (c2 != null && c2.tile != null) || 
                    (obs != null && obs.tile != null)) {
                    
                    rectBloque.set(x * mapLayer.tileWidth.toFloat(), y * mapLayer.tileHeight.toFloat(), mapLayer.tileWidth.toFloat(), mapLayer.tileHeight.toFloat())
                    if (playerRect.overlaps(rectBloque)) return true
                }
            }
        }
        return false
    }


    override fun render(delta: Float) {
        // --- 1. ACTUALIZAR LA LÓGICA (Update) ---
        
        // Primero calculamos su nueva velocidad (gravedad e input del teclado)
        player.update(delta)

        // Movemos en el eje X y comprobamos colisiones
        val oldX = player.position.x
        player.position.x += player.velocity.x * delta
        if (comprobarColisionesGenerico(player)) {
            player.position.x = oldX // Deshacemos el movimiento en X si chocamos
        }

        // Movemos en el eje Y y comprobamos colisiones
        val oldY = player.position.y
        player.position.y += player.velocity.y * delta
        if (comprobarColisionesGenerico(player)) {
            player.position.y = oldY // Deshacemos el movimiento en Y si chocamos
            player.velocity.y = 0f // Paramos de subir o caer
        }

        // Hacemos que la cámara siga al jugador
        camera.position.x = player.position.x
        camera.position.y = player.position.y
        

        // --- COMPROBAR ESTADOS DE JUEGO (GAME OVER / VICTORY) ---
        if (player.oxygen <= 0) {
            game.screen = GameOverScreen(game, player.score, player.timeSurvived, player.treasuresCollected)
            dispose()
            return // IMPORTANTE: Salir del render para no dibujar cosas destruidas
        }
        
        val mapaAnchoPixeles = mapLayer.width * mapLayer.tileWidth
        if (player.position.x > mapaAnchoPixeles - 100f) {
            game.screen = VictoryScreen(game, player.score, player.timeSurvived, player.treasuresCollected)
            dispose()
            return // IMPORTANTE: Salir del render
        }

        // --- LÍMITES DE LA CÁMARA ---
        // Evitamos que la cámara muestre áreas grises/vacías fuera del mapa
        // Usamos la capa ya cacheada
        val mapaAltoPixeles = mapLayer.height * mapLayer.tileHeight

        // Calculamos los bordes mínimos y máximos que la cámara puede ver
        val mitadCamaraAncho = camera.viewportWidth / 2f
        val mitadCamaraAlto = camera.viewportHeight / 2f

        // MathUtils.clamp restringe el valor entre un mínimo y un máximo
        camera.position.x = MathUtils.clamp(camera.position.x, mitadCamaraAncho, mapaAnchoPixeles - mitadCamaraAncho)
        camera.position.y = MathUtils.clamp(camera.position.y, mitadCamaraAlto, mapaAltoPixeles - mitadCamaraAlto)

        // Actualizamos las matemáticas de la cámara
        camera.update()


        // --- 3. DIBUJAR ANIMACIONES Y FONDO ---
        
        // Dibujamos el fondo estático (o podrías hacerlo con scroll más adelante)
        game.batch.projectionMatrix = camera.combined
        game.batch.begin()
        // Dibujamos el fondo cubriendo todo el ancho del mapa (100 tiles * 16px = 1600px)
        game.batch.draw(backgroundTexture, 0f, 0f, 1600f, 640f)
        game.batch.end()

        // Dibujamos el mapa encima del fondo
        mapRenderer.setView(camera)
        mapRenderer.render()

        // Determinamos qué animación usar según el estado actual del jugador
        val currentAnim = if (player.state == Player.State.UP || player.velocity.x != 0f) animSwimming else animIdle
        
        // Obtenemos el fotograma (frame) exacto que toca dibujar
        val currentFrame = currentAnim.getKeyFrame(player.stateTime)
        
        // Empezamos a dibujar al jugador y a los objetos
        game.batch.begin()
        
        // Invertimos el sprite si mira a la izquierda
        val flip = player.velocity.x < 0
        if (flip != currentFrame.isFlipX) {
            currentFrame.flip(true, false)
        }
        
        // Dibujamos el fotograma actual del jugador
        game.batch.draw(currentFrame, player.position.x, player.position.y, player.size, player.size)
        
        // --- 4. GESTIÓN DE OBJETOS (Dibujo y Colisión) ---
        val itemIter = items.iterator()
        while (itemIter.hasNext()) {
            val item = itemIter.next()
            game.batch.draw(item.region, item.position.x, item.position.y, item.size, item.size)
            
            if (com.badlogic.gdx.math.Intersector.overlaps(player.getBoundingRectangle(), item.getBoundingRectangle())) {
                if (item.type == ItemType.TREASURE) {
                    player.score += 1 // Subir de 1 en 1 como pides
                    player.treasuresCollected++
                } else if (item.type == ItemType.OXYGEN) {
                    player.oxygen += 20f
                    if (player.oxygen > 100f) player.oxygen = 100f
                }
                itemIter.remove()
            }
        }
        
        // --- 5. GESTIÓN DE MINAS (Apartado 4) ---
        val minaIter = minas.iterator()
        while (minaIter.hasNext()) {
            val mina = minaIter.next()
            mina.update(delta)
            
            if (mina.state == MinaState.ACTIVE) {
                // Dibujamos la mina normal
                game.batch.draw(mina.region, mina.position.x, mina.position.y, mina.size, mina.size)
                
                // Comprobamos proximidad (Apartat 4.2)
                val dist = Vector2.dst(player.position.x + player.size/2, player.position.y + player.size/2, 
                                       mina.position.x + mina.size/2, mina.position.y + mina.size/2)
                
                if (dist < 60f) { // Radio de activación
                    mina.state = MinaState.EXPLODING
                    player.oxygen -= 30f // Daño por explosión (Apartat 4.3)
                    println("¡BOOM! Mina activada")
                }
            } else if (mina.state == MinaState.EXPLODING) {
                // Dibujamos la animación de explosión
                val frame = explosionAnim.getKeyFrame(mina.explosionTime)
                game.batch.draw(frame, mina.position.x - 10, mina.position.y - 10, mina.size + 20, mina.size + 20)
            } else {
                minaIter.remove()
            }
        }
        
        game.batch.end()
        
        
        // --- 5. DIBUJAR EL HUD (Encima de todo) ---
        drawHUD()
    }

    // Metodo para dibujar los textos en pantalla (el HUD)
    private fun drawHUD() {
        game.batch.projectionMatrix = hudCamera.combined
        game.batch.begin()
        
        // Pongo un fondo oscuro transparente arriba para que se lea bien el texto blanco o amarillo
        game.batch.setColor(0f, 0f, 0f, 0.6f)
        game.batch.draw(idleSheet, 0f, hudViewport.worldHeight - 60f, hudViewport.worldWidth, 60f, 0, 0, 1, 1, false, false)
        game.batch.setColor(1f, 1f, 1f, 1f)

        // Dibujo primero los textos en negro un poco movidos para hacer el efecto de sombra
        // y luego en color normal encima. Es un truco que aprendí para que resalte.
        
        // Puntos (Sombra)
        font.color = com.badlogic.gdx.graphics.Color.BLACK
        font.draw(game.batch, "PUNTOS: ${player.score}", 32f, hudViewport.worldHeight - 17f)
        // Puntos (Normal)
        font.color = com.badlogic.gdx.graphics.Color.YELLOW
        font.draw(game.batch, "PUNTOS: ${player.score}", 30f, hudViewport.worldHeight - 15f)
        
        // Oxígeno (cambia de color si hay menos del 30%)
        val oxyColor = if (player.oxygen > 30) com.badlogic.gdx.graphics.Color.CYAN else com.badlogic.gdx.graphics.Color.RED
        
        // Oxígeno (Sombra)
        font.color = com.badlogic.gdx.graphics.Color.BLACK
        font.draw(game.batch, "OXÍGENO: ${player.oxygen.toInt()}%", hudViewport.worldWidth - 248f, hudViewport.worldHeight - 17f)
        // Oxígeno (Normal)
        font.color = oxyColor
        font.draw(game.batch, "OXÍGENO: ${player.oxygen.toInt()}%", hudViewport.worldWidth - 250f, hudViewport.worldHeight - 15f)
        
        game.batch.end()
    }


    override fun show() {}
    override fun resize(width: Int, height: Int) {
        // Actualizamos los viewports con el nuevo tamaño de ventana
        viewport.update(width, height)
        hudViewport.update(width, height, true)
    }
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}

    override fun dispose() {
        // Liberamos la memoria al salir de la pantalla
        map.dispose()
        mapRenderer.dispose()
        idleSheet.dispose()
        swimmingSheet.dispose()
        backgroundTexture.dispose()
        treasureTexture.dispose()
        oxygenTexture.dispose()
        explosionTexture.dispose()
        font.dispose()
        debugRenderer.dispose()
    }
}