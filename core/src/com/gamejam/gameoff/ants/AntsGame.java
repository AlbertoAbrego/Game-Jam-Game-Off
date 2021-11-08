package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class AntsGame implements Screen {
    final GameManager gameManager;
    OrthographicCamera camera;
    private MapManager map;
    private IsometricTiledMapRenderer mapRenderer;
    private Player player;
    private Ant ant, ant2;
    private HUD hud;
    private int playerSpeedUpAndDown, playerSpeedLeftAndRight;

    public AntsGame(final GameManager gameManager){
        this.gameManager = gameManager;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1920,1080);
        playerSpeedUpAndDown = 70;
        playerSpeedLeftAndRight = 100;
        map = new MapManager();
        mapRenderer = map.getMapRenderer();
        //aqui estamos posicionando correctamente el mapa
//        mapRenderer.getMap().getLayers().get(0).setOffsetX(-640);
        player = new Player();
        ant = new Ant(1024,0);
        ant2 = new Ant(300,0);
        hud = new HUD();
        camera.translate(-960,-530);
//        map = new TmxMapLoader().load("../../tiledmaps/map.tmx");
//        mapRenderer = new IsometricTiledMapRenderer(map);
        //map.getTileSets().getTileSet(0).getTile(1).setTextureRegion(new TextureRegion(new Texture(Gdx.files.internal("../../android/assets/isometric tiles/bamboo06.png")),5,5,256,128));

//        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
//        TiledMapTileLayer.Cell celda = layer.getCell(1,2);
//        TiledMapTileLayer.Cell celda2 = new TiledMapTileLayer.Cell();
//        TiledMapTileSet tileSet = map.getTileSets().getTileSet(0);
//        System.out.println(tileSet.size());
//        celda2.setTile(tileSet.getTile(18));
//        layer.setCell(0,3,celda2);
//        layer.setCell(0,4,celda2);
//        layer.setCell(0,5,celda2);
//        layer.setCell(0,6,celda2);

        //MapManager mapManager = new MapManager();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        camera.zoom = 0.7f;
        mapRenderer.setView(camera);
        mapRenderer.render();
        gameManager.batch.setProjectionMatrix(camera.combined);
        gameManager.batch.begin();
        gameManager.batch.draw(player.getPlayerImage(),player.getPlayerHitBox().x,player.getPlayerHitBox().y);
        gameManager.batch.draw(ant.getAntImage(),ant.getAntHitBox().x,ant.getAntHitBox().y);
        gameManager.batch.draw(ant2.getAntImage(),ant2.getAntHitBox().x,ant2.getAntHitBox().y);
        gameManager.font.draw(gameManager.batch,hud.getAntCounterText(),hud.getaCTx(), hud.getaCTy());
        gameManager.batch.draw(hud.getAntCounterIcon(),hud.getAntCounter().x,hud.getAntCounter().y);
        gameManager.batch.draw(hud.getEnergyBarImage(), hud.getEnergyBar().x,hud.getEnergyBar().y);
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            camera.translate(0,playerSpeedUpAndDown*Gdx.graphics.getDeltaTime());
            player.getPlayerHitBox().y += playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
            hud.getAntCounter().y += playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
            hud.getEnergyBar().y += playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
            hud.setaCTy(playerSpeedUpAndDown*Gdx.graphics.getDeltaTime());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            camera.translate(0,-playerSpeedUpAndDown * Gdx.graphics.getDeltaTime());
            player.getPlayerHitBox().y -= playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
            hud.getAntCounter().y -= playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
            hud.getEnergyBar().y -= playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
            hud.setaCTy(-playerSpeedUpAndDown*Gdx.graphics.getDeltaTime());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            camera.translate(-playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(),0);
            player.getPlayerHitBox().x -= playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
            hud.getAntCounter().x -= playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
            hud.getEnergyBar().x -= playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
            hud.setaCTx(-playerSpeedLeftAndRight*Gdx.graphics.getDeltaTime());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            camera.translate(playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(),0);
            player.getPlayerHitBox().x += playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
            hud.getAntCounter().x += playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
            hud.getEnergyBar().x += playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
            hud.setaCTx(playerSpeedLeftAndRight*Gdx.graphics.getDeltaTime());
        }
        if(ant.getAntHitBox().overlaps(player.getPlayerHitBox())){
            hud.addAntCounter();
        }
        gameManager.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
