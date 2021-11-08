package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final GameManager gameManager;
    OrthographicCamera camera;

    public MainMenuScreen(final GameManager gameManager){
        this.gameManager = gameManager;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920,1080);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f,0.2f,0.2f,1);
        camera.update();
        gameManager.batch.setProjectionMatrix(camera.combined);
        gameManager.batch.begin();
        gameManager.font.draw(gameManager.batch, "Hello...",1000,900);
        gameManager.batch.end();
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            gameManager.setScreen((new AntsGame(gameManager)));
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
