package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class ScoreItem {
    final Rectangle scoreItemHitBox;
    final Texture scoreItemImage;

    public ScoreItem(float x, float y){
        scoreItemHitBox = new Rectangle();
        scoreItemHitBox.width = 64;
        scoreItemHitBox.height = 64;
        scoreItemHitBox.x = x;
        scoreItemHitBox.y = y;
        scoreItemImage = new Texture(Gdx.files.internal("leaf.png"));
    }

    public Rectangle getScoreItemHitBox() { return scoreItemHitBox; }

    public Texture getScoreItemImage() { return scoreItemImage; }
}
