package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

import java.util.Iterator;

public class AntsGame implements Screen {
    final GameManager gameManager;
    OrthographicCamera camera;
    final MapManager map;
    final IsometricTiledMapRenderer mapRenderer;
    final Player player;
    final AntsSpawner antsSpawner;
    final ScoreItemSpawner scoreItemSpawner;
    final EnergyItemSpawner energyItemSpawner;
    final InsectSpawner insectSpawner;
    final EnemySpawner enemySpawner;
    final HUD hud;
    final int playerSpeedUpAndDown, playerSpeedLeftAndRight;
    final Anthill anthill;
    final SpawnedInMap spawnedInMap;

    public AntsGame(final GameManager gameManager){
        this.gameManager = gameManager;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1920,1080);
        System.out.println(camera.position);
        playerSpeedUpAndDown = 64;
        playerSpeedLeftAndRight = 128;
        map = new MapManager();
        mapRenderer = map.getMapRenderer();
        //aqui estamos posicionando correctamente el mapa
        mapRenderer.getMap().getLayers().get(0).setOffsetX(-3840);
        player = new Player();
        hud = new HUD();
        spawnedInMap = new SpawnedInMap();
        antsSpawner = new AntsSpawner(spawnedInMap);
        scoreItemSpawner = new ScoreItemSpawner(spawnedInMap);
        energyItemSpawner = new EnergyItemSpawner(spawnedInMap);
        insectSpawner = new InsectSpawner(spawnedInMap);
        enemySpawner = new EnemySpawner();
        anthill = new Anthill();
        camera.translate(-960+player.getPlayerHitBox().getWidth(),-540+player.getPlayerHitBox().getHeight());
//        camera.translate(3008,-476);
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
//        camera.zoom = 3f;
        mapRenderer.setView(camera);
        mapRenderer.render();
        gameManager.batch.setProjectionMatrix(camera.combined);
        gameManager.batch.begin();
        antsSpawner.drawAnts(gameManager);
        scoreItemSpawner.drawItems(gameManager);
        energyItemSpawner.drawItems(gameManager);
        gameManager.batch.draw(
                anthill.getAnthillImage(),
                anthill.getAnthillHitBox().x,
                anthill.getAnthillHitBox().y,
                anthill.getAnthillHitBox().getWidth(),
                anthill.getAnthillHitBox().getHeight()
        );
        insectSpawner.drawInsects(gameManager);
        insectSpawner.moveInsects(map);
        enemySpawner.drawEnemies(gameManager);
        enemySpawner.moveEnemies(map);
        if(player.hasAntBehind()){
            player.drawAntFollowingPlayer(gameManager);
        }
        gameManager.font.draw(
                gameManager.batch,
                hud.getAntCounterText(),
                hud.getaCTx(),
                hud.getaCTy()
        );
        gameManager.font.draw(
                gameManager.batch,
                hud.getScore(),
                hud.getsTx(),
                hud.getsTy()
        );
        gameManager.batch.draw(
                player.getPlayerImage(),
                player.getPlayerHitBox().x,
                player.getPlayerHitBox().y,
                player.getPlayerHitBox().getWidth(),
                player.getPlayerHitBox().getHeight()
        );
        gameManager.batch.draw(
                hud.getAntCounterIcon(),
                hud.getAntCounterRectangle().x,
                hud.getAntCounterRectangle().y,
                hud.getAntCounterRectangle().getWidth(),
                hud.getAntCounterRectangle().getHeight()
        );
        gameManager.batch.draw(
                hud.getEnergyImage(),
                hud.getEnergyRectangle().x,
                hud.getEnergyRectangle().y,
                hud.getEnergyRectangle().getWidth(),
                hud.getEnergyRectangle().getHeight()
        );
        gameManager.batch.draw(
                hud.getEnergyBarImage(),
                hud.getEnergyBarRectangle().x,
                hud.getEnergyBarRectangle().y,
                hud.getEnergyBarRectangle().getWidth(),
                hud.getEnergyBarRectangle().getHeight()
        );
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.LEFT) && map.isNotOutOfLimits(player.getPlayerHitBox(),0)){// up left
            if(player.getEnergy()>0) {
                camera.translate(0, playerSpeedUpAndDown * Gdx.graphics.getDeltaTime());
                player.getPlayerHitBox().y += playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().y > player.getAntBehindY() + 64) {
                        player.moveAntFollowingPlayer(playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
                    }
                }
                camera.translate(-playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), 0);
                player.getPlayerHitBox().x -= playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().x < player.getAntBehindX() - 128) {
                        player.moveAntFollowingPlayer(-playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
                    }
                }
                hud.updateEnergy(player.getEnergy()/player.getNumberOfAnts());
                hud.moveHUD(playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
                hud.moveHUD(-playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
            }
        }else if(Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.RIGHT) && map.isNotOutOfLimits(player.getPlayerHitBox(),1)){// up right
            if(player.getEnergy()>0) {
                camera.translate(0, playerSpeedUpAndDown * Gdx.graphics.getDeltaTime());
                player.getPlayerHitBox().y += playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().y > player.getAntBehindY() + 64) {
                        player.moveAntFollowingPlayer(playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
                    }
                }
                camera.translate(playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), 0);
                player.getPlayerHitBox().x += playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().x > player.getAntBehindX() + 128) {
                        player.moveAntFollowingPlayer(playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
                    }
                }
                hud.updateEnergy(player.getEnergy()/player.getNumberOfAnts());
                hud.moveHUD(playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
                hud.moveHUD(playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
            }
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.LEFT) && map.isNotOutOfLimits(player.getPlayerHitBox(),3)){//down left
            if (player.getEnergy() > 0) {
                camera.translate(0, -playerSpeedUpAndDown * Gdx.graphics.getDeltaTime());
                player.getPlayerHitBox().y -= playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().y < player.getAntBehindY() - 64) {
                        player.moveAntFollowingPlayer(-playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
                    }
                }
                camera.translate(-playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), 0);
                player.getPlayerHitBox().x -= playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().x < player.getAntBehindX() - 128) {
                        player.moveAntFollowingPlayer(-playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
                    }
                }
                hud.updateEnergy(player.getEnergy()/player.getNumberOfAnts());
                hud.moveHUD(-playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
                hud.moveHUD(-playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
            }
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.RIGHT) && map.isNotOutOfLimits(player.getPlayerHitBox(),2)){// down right
            if(player.getEnergy()>0) {
                camera.translate(0, -playerSpeedUpAndDown * Gdx.graphics.getDeltaTime());
                player.getPlayerHitBox().y -= playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().y < player.getAntBehindY() - 64) {
                        player.moveAntFollowingPlayer(-playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
                    }
                }
                camera.translate(playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), 0);
                player.getPlayerHitBox().x += playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().x > player.getAntBehindX() + 128) {
                        player.moveAntFollowingPlayer(playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
                    }
                }
                hud.updateEnergy(player.getEnergy()/player.getNumberOfAnts());
                hud.moveHUD(-playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
                hud.moveHUD(playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
            }
        }else if(Gdx.input.isKeyPressed(Input.Keys.UP) && map.isNotOutOfLimits(player.getPlayerHitBox(),0) && map.isNotOutOfLimits(player.getPlayerHitBox(),1)){//up
            if(player.getEnergy()>0) {
                camera.translate(0, playerSpeedUpAndDown * Gdx.graphics.getDeltaTime());
                player.getPlayerHitBox().y += playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().y > player.getAntBehindY() + 64) {
                        player.moveAntFollowingPlayer(playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
                    }
                }
                hud.updateEnergy(player.getEnergy()/player.getNumberOfAnts());
                hud.moveHUD(playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
            }
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && map.isNotOutOfLimits(player.getPlayerHitBox(),2) && map.isNotOutOfLimits(player.getPlayerHitBox(),3)){//down
            if (player.getEnergy() > 0) {
                camera.translate(0, -playerSpeedUpAndDown * Gdx.graphics.getDeltaTime());
                player.getPlayerHitBox().y -= playerSpeedUpAndDown * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().y < player.getAntBehindY() - 64) {
                        player.moveAntFollowingPlayer(-playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
                    }
                }
                hud.updateEnergy(player.getEnergy()/player.getNumberOfAnts());
                hud.moveHUD(-playerSpeedUpAndDown * Gdx.graphics.getDeltaTime(), false);
            }
        }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && map.isNotOutOfLimits(player.getPlayerHitBox(),0) && map.isNotOutOfLimits(player.getPlayerHitBox(),3)){//left
            if(player.getEnergy()>0) {
                camera.translate(-playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), 0);
                player.getPlayerHitBox().x -= playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().x < player.getAntBehindX() - 128) {
                        player.moveAntFollowingPlayer(-playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
                    }
                }
                hud.updateEnergy(player.getEnergy()/player.getNumberOfAnts());
                hud.moveHUD(-playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
            }
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && map.isNotOutOfLimits(player.getPlayerHitBox(),1) && map.isNotOutOfLimits(player.getPlayerHitBox(), 2)){//right
            if(player.getEnergy()>0) {
                camera.translate(playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), 0);
                player.getPlayerHitBox().x += playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime();
                player.updateEnergy(Gdx.graphics.getDeltaTime());
                if (player.hasAntBehind()) {
                    if (player.getPlayerHitBox().x > player.getAntBehindX() + 128) {
                        player.moveAntFollowingPlayer(playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
                    }
                }
                hud.updateEnergy(player.getEnergy()/player.getNumberOfAnts());
                hud.moveHUD(playerSpeedLeftAndRight * Gdx.graphics.getDeltaTime(), true);
            }
        }
        gameManager.batch.end();

        

        Iterator<Ant> antIterator = antsSpawner.ants.iterator();
        while(antIterator.hasNext()){
            Ant antInMap = antIterator.next();
            if(antInMap.getAntHitBox().overlaps(player.getPlayerHitBox())){
                hud.addAntCounter();
                player.addAnt();
                spawnedInMap.removePosition(antInMap.getPosition());
                antIterator.remove();
            }
        }

        Iterator<ScoreItem> scoreItemIterator = scoreItemSpawner.scoreItems.iterator();
        while(scoreItemIterator.hasNext()){
            ScoreItem scoreItemInMap = scoreItemIterator.next();
            if(scoreItemInMap.getScoreItemHitBox().overlaps(player.getPlayerHitBox()) && player.antsCanCarry()>=scoreItemInMap.getAntsRequired()){
                player.addItem(scoreItemInMap.getAntsRequired());
                player.addPoints(scoreItemInMap.getPoints());
                spawnedInMap.removePosition(scoreItemInMap.getPosition());
                scoreItemIterator.remove();
            }
        }

        Iterator<Insect> insectIterator = insectSpawner.insects.iterator();
        while(insectIterator.hasNext()){
            Insect insectInMap = insectIterator.next();
            if(insectInMap.getInsectHitBox().overlaps(player.getPlayerHitBox()) && player.antsCanCarry()>=insectInMap.getAntsRequired()){
                player.addItem(insectInMap.getAntsRequired());
                player.addPoints(insectInMap.getPoints());
                spawnedInMap.removePosition(insectInMap.getPosition());
                insectIterator.remove();
            }
        }

        Iterator<EnergyItem> energyItemIterator = energyItemSpawner.energyItems.iterator();
        while(energyItemIterator.hasNext()){
            EnergyItem energyItemInMap = energyItemIterator.next();
            if(energyItemInMap.getEnergyItemHitBox().overlaps(player.getPlayerHitBox())){
                player.addEnergy(energyItemInMap.getEnergyAmount());
                spawnedInMap.removePosition(energyItemInMap.getPosition());
                energyItemIterator.remove();
            }
        }

        if(anthill.getAnthillHitBox().overlaps(player.getPlayerHitBox())){
            player.addScore(player.getPoints());
            player.resetPoints();
            hud.updateScore(player.getScore());
        }
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
