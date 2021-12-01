package com.gamejam.gameoff.ants;

import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

public class MapManager {
    private TiledMap map;
    private IsometricTiledMapRenderer mapRenderer;

    public MapManager(){
        map = new TmxMapLoader().load("../../tiledmaps/map2.tmx");
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

    public boolean isNotOutOfLimits(Rectangle body, int vertex){
        switch (vertex){
            case 0:
                float x0 = body.x;
                float y0 = body.y+body.height;
                if(x0 <= 2 * (y0 - 64) - 3840){
                    return false;
                }
                if(y0 >= ((x0 + 3840)/2) +64){
                    return false;
                }
                break;
            case 1:
                float x1 = body.x + body.width;
                float y1 = body.y + body.height;
                if(x1 >= -2 * (y1 - 3840 - 64) - 3840){
                    return false;
                }
                if(y1 >= -((x1 + 3840)/2) + 3840 + 64){
                    return false;
                }
                break;
            case 2:
                float x2 = body.x + body.width;
                float y2 = body.y;
                if(x2 >= 2 * (y2 + 3840 - 64) - 3840){
                    return false;
                }
                if(y2 <= ((x2 + 3840)/2) - 3840 + 64){
                    return false;
                }
                break;
            case 3:
                float x3 = body.x;
                float y3 = body.y;
                if(x3 <= -2 * (y3 - 64) - 3840){
                    return false;
                }
                if(y3 <= -((x3 + 3840)/2) + 64){
                    return false;
                }
                break;
            default:
                return true;
        }
        return true;
    }
}
