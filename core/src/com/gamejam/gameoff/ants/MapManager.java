package com.gamejam.gameoff.ants;

import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class MapManager {
    private TiledMap map;
    private IsometricTiledMapRenderer mapRenderer;

    public MapManager(){
        map = new TmxMapLoader().load("../../tiledmaps/map.tmx");
        mapRenderer = new IsometricTiledMapRenderer(map);
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public IsometricTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }

    public void setMapRenderer(IsometricTiledMapRenderer mapRenderer) {
        this.mapRenderer = mapRenderer;
    }
}
