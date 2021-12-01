package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ScoreItem {
    final Rectangle scoreItemHitBox;
    final Texture scoreItemImage;
    final Vector2 position;
    private int points;
    final int antsRequired;

    public ScoreItem(float a, float b, int level){
        antsRequired = level;
        position = new Vector2(a,b);
        scoreItemHitBox = new Rectangle();
        scoreItemHitBox.width = 64;
        scoreItemHitBox.height = 64;
        scoreItemHitBox.x = 128*a + 128*b + 128 - scoreItemHitBox.width/2 -3840;
        scoreItemHitBox.y = -64*a + 64*b + 64 - scoreItemHitBox.height/2;
        scoreItemImage = new Texture(Gdx.files.internal("item_"+level+".png"));
        switch (antsRequired){
            case 1: points = 10; break;
            case 2: points = 25; break;
            case 3: points = 40; break;
        }
    }

    public Vector2 getPosition() { return position; }

    public Rectangle getScoreItemHitBox() { return scoreItemHitBox; }

    public Texture getScoreItemImage() { return scoreItemImage; }

    public int getPoints() { return points; }

    public int getAntsRequired() { return antsRequired; }
}
