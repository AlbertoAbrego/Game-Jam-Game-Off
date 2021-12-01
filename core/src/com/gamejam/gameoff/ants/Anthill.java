package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Anthill {
    private Texture anthillImage;
    private Rectangle anthillHitBox;

    public Anthill(){
        anthillHitBox = new Rectangle();
        anthillHitBox.width = 65;
        anthillHitBox.height = 50;
        anthillHitBox.x = 128-anthillHitBox.width/2;
        anthillHitBox.y = 64-anthillHitBox.height/2;
        anthillImage = new Texture(Gdx.files.internal("anthill.png"));
    }

    public Texture getAnthillImage() { return anthillImage; }

    public Rectangle getAnthillHitBox() { return anthillHitBox; }
}
