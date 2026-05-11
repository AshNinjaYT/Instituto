package com.achraf.submarinista.screens

import com.achraf.submarinista.SubmarinistGame
import com.achraf.submarinista.entities.*
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
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
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport

// Esta es la pantalla principal donde jugamos
class GameScreen(val game: SubmarinistGame) : Screen {

    // 1. VARIABLES BÁSICAS
    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var hudCamera: OrthographicCamera
    private lateinit var hudViewport: Viewport
    private lateinit var font: BitmapFont
    private lateinit var whitePixel: Texture
    
    // Variables para el mapa de Tiled que he hecho
    private lateinit var map: TiledMap
    private lateinit var mapRenderer: OrthogonalTiledMapRenderer
    private lateinit var mapLayer: TiledMapTileLayer
    private lateinit var mapLayer2: TiledMapTileLayer
    private lateinit var obstaclesLayer: TiledMapTileLayer
    private val rectBloque = Rectangle()
    
    // Variables para los sprites del personaje
    private lateinit var idleSheet: Texture
    private lateinit var swimmingSheet: Texture
    private lateinit var backgroundTexture: Texture
    private lateinit var animIdle: com.badlogic.gdx.graphics.g2d.Animation<com.badlogic.gdx.graphics.g2d.TextureRegion>
    private lateinit var animSwimming: com.badlogic.gdx.graphics.g2d.Animation<com.badlogic.gdx.graphics.g2d.TextureRegion>
    
    // --- APARTADO 3 y 4: Texturas para objetos ---
    private lateinit var treasureTexture: Texture
    private lateinit var oxygenTexture: Texture
    private lateinit var explosionTexture: Texture
    private lateinit var explosionAnim: com.badlogic.gdx.graphics.g2d.Animation<com.badlogic.gdx.graphics.g2d.TextureRegion>
    
    // Listas para almacenar los elementos del mapa
    private val items = Array<Item>()
    private val minas = Array<Mina>()
    
    // Nuestro objeto jugador
    private lateinit var player: Player
    private lateinit var debugRenderer: com.badlogic.gdx.graphics.glutils.ShapeRenderer

    init {
        // --- INICIALIZACIÓN ---
        camera = OrthographicCamera()
        viewport = FitViewport(400f, 300f, camera)

        hudCamera = OrthographicCamera()
        hudViewport = FitViewport(800f, 600f, hudCamera) // Dimensiones base, se ajustan en resize
        
        font = BitmapFont()
        font.data.setScale(1.8f)
        font.color = Color.WHITE

        // Cargamos el mapa
        map = TmxMapLoader().load("map.tmx")
        mapRenderer = OrthogonalTiledMapRenderer(map)
        
        mapLayer = getLayerByName(map, "Paredes") ?: throw IllegalArgumentException("No se encontró la capa Paredes")
        mapLayer2 = getLayerByName(map, "Paredes2") ?: mapLayer
        obstaclesLayer = getLayerByName(map, "Obstaculos") ?: mapLayer

        // Cargamos imágenes
        backgroundTexture = Texture("background.png")
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        idleSheet = Texture("player-idle.png")
        idleSheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        swimmingSheet = Texture("player-swiming.png")
        swimmingSheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        
        // Setup de animaciones del jugador
        if (idleSheet.width >= 80 && idleSheet.height >= 80) {
            val idleFrames = com.badlogic.gdx.graphics.g2d.TextureRegion.split(idleSheet, 80, 80)[0]
            animIdle = com.badlogic.gdx.graphics.g2d.Animation(0.15f, *idleFrames)
        } else {
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

        // Creamos una textura blanca de 1x1 para dibujar rectángulos de colores (como el fondo del HUD)
        val pixmap = com.badlogic.gdx.graphics.Pixmap(1, 1, com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.WHITE)
        pixmap.fill()
        whitePixel = Texture(pixmap)
        pixmap.dispose()

        // Texturas de items
        treasureTexture = Texture("treasure.png")
        oxygenTexture = Texture("oxygen.png")
        explosionTexture = Texture("explosion-small.png")
        
        val expSize = explosionTexture.height
        val expFrames = com.badlogic.gdx.graphics.g2d.TextureRegion.split(explosionTexture, expSize, expSize)[0]
        explosionAnim = com.badlogic.gdx.graphics.g2d.Animation(0.08f, *expFrames)
        
        // Cargar objetos del Tiled
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

        val spawnPos = findSafeSpawn()
        player = Player(spawnPos.x, spawnPos.y)
        debugRenderer = com.badlogic.gdx.graphics.glutils.ShapeRenderer()
    }

    private fun findSafeSpawn(): Vector2 {
        val tempPlayer = Player(0f, 0f)
        for (tx in 3..20) {
            for (ty in 8..15) { 
                val posX = tx * mapLayer.tileWidth.toFloat()
                val posY = ty * mapLayer.tileHeight.toFloat()
                tempPlayer.position.set(posX, posY)
                if (!comprobarColisionesGenerico(tempPlayer)) return Vector2(posX, posY)
            }
        }
        return Vector2(300f, 300f)
    }

    private fun getLayerByName(map: TiledMap, name: String): TiledMapTileLayer? {
        for (layer in map.layers) {
            if (layer.name == name && layer is TiledMapTileLayer) return layer
            if (layer is com.badlogic.gdx.maps.MapGroupLayer) {
                for (child in layer.layers) {
                    if (child.name == name && child is TiledMapTileLayer) return child
                }
            }
        }
        return null
    }

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
                if ((c1 != null && c1.tile != null) || (c2 != null && c2.tile != null) || (obs != null && obs.tile != null)) {
                    rectBloque.set(x * mapLayer.tileWidth.toFloat(), y * mapLayer.tileHeight.toFloat(), mapLayer.tileWidth.toFloat(), mapLayer.tileHeight.toFloat())
                    if (playerRect.overlaps(rectBloque)) return true
                }
            }
        }
        return false
    }

    override fun render(delta: Float) {
        // Limpiamos la pantalla (Apartado 1 del feedback)
        Gdx.gl.glClearColor(0f, 0f, 0.1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        player.update(delta)

        val oldX = player.position.x
        player.position.x += player.velocity.x * delta
        if (comprobarColisionesGenerico(player)) player.position.x = oldX

        val oldY = player.position.y
        player.position.y += player.velocity.y * delta
        if (comprobarColisionesGenerico(player)) {
            player.position.y = oldY
            player.velocity.y = 0f
        }

        camera.position.set(player.position.x, player.position.y, 0f)
        
        if (player.oxygen <= 0) {
            game.screen = GameOverScreen(game, player.score, player.timeSurvived, player.treasuresCollected)
            return
        }
        
        val mapaAnchoPixeles = mapLayer.width * mapLayer.tileWidth
        if (player.position.x > mapaAnchoPixeles - 100f) {
            game.screen = VictoryScreen(game, player.score, player.timeSurvived, player.treasuresCollected)
            return
        }

        val mapaAltoPixeles = mapLayer.height * mapLayer.tileHeight
        camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2f, mapaAnchoPixeles - camera.viewportWidth / 2f)
        camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2f, mapaAltoPixeles - camera.viewportHeight / 2f)
        camera.update()

        game.batch.projectionMatrix = camera.combined
        game.batch.begin()
        game.batch.draw(backgroundTexture, 0f, 0f, 1600f, 640f)
        game.batch.end()

        mapRenderer.setView(camera)
        mapRenderer.render()

        val currentAnim = if (player.state == Player.State.UP || player.velocity.x != 0f) animSwimming else animIdle
        val currentFrame = currentAnim.getKeyFrame(player.stateTime)
        
        game.batch.begin()
        if ((player.velocity.x < 0) != currentFrame.isFlipX) currentFrame.flip(true, false)
        game.batch.draw(currentFrame, player.position.x, player.position.y, player.size, player.size)
        
        val itemIter = items.iterator()
        while (itemIter.hasNext()) {
            val item = itemIter.next()
            game.batch.draw(item.region, item.position.x, item.position.y, item.size, item.size)
            if (com.badlogic.gdx.math.Intersector.overlaps(player.getBoundingRectangle(), item.getBoundingRectangle())) {
                if (item.type == ItemType.TREASURE) {
                    player.score += 1
                    player.treasuresCollected++
                } else {
                    player.oxygen += 20f
                    if (player.oxygen > 100f) player.oxygen = 100f
                }
                itemIter.remove()
            }
        }
        
        val minaIter = minas.iterator()
        while (minaIter.hasNext()) {
            val mina = minaIter.next()
            mina.update(delta, explosionAnim)
            
            if (mina.state == MinaState.ACTIVE) {
                game.batch.draw(mina.region, mina.position.x, mina.position.y, mina.size, mina.size)
                val dist = Vector2.dst(player.position.x + player.size/2, player.position.y + player.size/2, 
                                       mina.position.x + mina.size/2, mina.position.y + mina.size/2)
                if (dist < 60f) {
                    mina.state = MinaState.EXPLODING
                    player.oxygen -= 30f
                }
            } else if (mina.state == MinaState.EXPLODING) {
                val frame = explosionAnim.getKeyFrame(mina.explosionTime)
                game.batch.draw(frame, mina.position.x - 10, mina.position.y - 10, mina.size + 20, mina.size + 20)
            } else {
                minaIter.remove()
            }
        }
        game.batch.end()
        drawHUD()
    }

    private fun drawHUD() {
        game.batch.projectionMatrix = hudCamera.combined
        game.batch.begin()
        game.batch.setColor(0f, 0f, 0f, 0.6f)
        game.batch.draw(whitePixel, 0f, hudViewport.worldHeight - 60f, hudViewport.worldWidth, 60f)
        game.batch.setColor(1f, 1f, 1f, 1f)

        font.color = Color.BLACK
        font.draw(game.batch, "PUNTOS: ${player.score}", 32f, hudViewport.worldHeight - 17f)
        font.color = Color.YELLOW
        font.draw(game.batch, "PUNTOS: ${player.score}", 30f, hudViewport.worldHeight - 15f)
        
        val oxyColor = if (player.oxygen > 30) Color.CYAN else Color.RED
        font.color = Color.BLACK
        font.draw(game.batch, "OXÍGENO: ${player.oxygen.toInt()}%", hudViewport.worldWidth - 248f, hudViewport.worldHeight - 17f)
        font.color = oxyColor
        font.draw(game.batch, "OXÍGENO: ${player.oxygen.toInt()}%", hudViewport.worldWidth - 250f, hudViewport.worldHeight - 15f)
        game.batch.end()
    }

    override fun show() {}
    override fun resize(width: Int, height: Int) {
        viewport.update(width, height)
        hudViewport.update(width, height, true)
    }
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}

    override fun dispose() {
        map.dispose()
        mapRenderer.dispose()
        idleSheet.dispose()
        whitePixel.dispose()
        swimmingSheet.dispose()
        backgroundTexture.dispose()
        treasureTexture.dispose()
        oxygenTexture.dispose()
        explosionTexture.dispose()
        font.dispose()
        debugRenderer.dispose()
    }
}