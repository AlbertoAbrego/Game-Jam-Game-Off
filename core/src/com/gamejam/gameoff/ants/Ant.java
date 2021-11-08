package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Ant {
    private Rectangle antHitBox;
    private Texture antImage;

    public Ant(float x, float y) {
        antHitBox = new Rectangle();
        antHitBox.width = 64;
        antHitBox.height = 64;
        antHitBox.x = x;
        antHitBox.y = y;
        antImage = new Texture(Gdx.files.internal("antIcon128.png"));
    }

    public Rectangle getAntHitBox() {
        return antHitBox;
    }

    public Texture getAntImage(){
        return antImage;
    }
}
