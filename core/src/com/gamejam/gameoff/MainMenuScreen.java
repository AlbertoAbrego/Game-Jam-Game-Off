package com.gamejam.gameoff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final MainMenu game;
    OrthographicCamera camera;

    public MainMenuScreen(final MainMenu game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0.2f,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch,"Welcome to my first game", 100,150);
        game.font.draw(game.batch,"Press any key to continue...", 100,100);
        game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            game.setScreen((new GameScreen(game)));
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