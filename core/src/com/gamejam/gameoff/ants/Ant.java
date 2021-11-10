package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Ant {
    final Rectangle antHitBox;
    final Texture antImage;

    public Ant(float x, float y) {
        antHitBox = new Rectangle();
        antHitBox.width = 128;
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

    public void setAntImage(String newPath){
        antImage.load(new Texture(Gdx.files.internal(newPath)).getTextureData());
    }
}
