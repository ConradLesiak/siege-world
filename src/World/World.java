package com.rgs.siegeworld.World;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.rgs.siegeworld.Game;
import com.rgs.siegeworld.Player.Player;
import com.rgs.siegeworld.World.Objects.Objects;

public class World {

    public static TiledMap map;
    public static TiledMapTileLayer groundLayer;
    public static TiledMapTileLayer groundLayer2;
    public static TiledMapTileLayer groundLayer3;
    public static TiledMapTileLayer wallsLayer1;
    public static TiledMapTileLayer wallsLayer2;
    public static boolean renderWalls = false;
    public static OrthogonalTiledMapRenderer renderer;
    public static int tilePosX = 0;
    public static int tilePosY = 0;
    Objects objects = new Objects();

    public void create() {
        TmxMapLoader.Parameters par = new TmxMapLoader.Parameters();
        par.textureMinFilter = Texture.TextureFilter.Nearest;
        par.textureMagFilter = Texture.TextureFilter.Nearest;
        map = new TmxMapLoader().load("World/world.tmx", par);
        groundLayer = (TiledMapTileLayer) map.getLayers().get("Ground");
        groundLayer2 = (TiledMapTileLayer) map.getLayers().get("Ground2");
        groundLayer3 = (TiledMapTileLayer) map.getLayers().get("Ground3");

        wallsLayer1 = (TiledMapTileLayer) map.getLayers().get("Walls");
        wallsLayer2 = (TiledMapTileLayer) map.getLayers().get("Walls");

        renderer = new OrthogonalTiledMapRenderer(map, Game.stage.getBatch());
        objects.create();
    }

    public void render() {
        renderer.setView((OrthographicCamera) Game.stage.getCamera());
        renderer.render();
        tilePosX = (int)Player.playerPos.x / 300;
        tilePosY = (int)Player.playerPos.y / 300;
        objects.render();
    }

    public void update() {

    }

    public void renderWalls() {     //Render walls over player for "hide" effect.
        if(renderWalls) {
            renderer.renderTileLayer(wallsLayer2);
        }
    }
}
