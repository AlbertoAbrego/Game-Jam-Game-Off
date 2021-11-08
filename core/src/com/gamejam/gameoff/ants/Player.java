package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private Rectangle playerHitBox;
    private Texture playerImage;
    public Player() {
        playerHitBox = new Rectangle();
        playerHitBox.width = 64;
        playerHitBox.height = 64;
        playerHitBox.x =0 ;
        playerHitBox.y = 0;
        playerImage = new Texture(Gdx.files.internal("player.png"));
    }

    public Rectangle getPlayerHitBox() {
        return playerHitBox;
    }

    public Texture getPlayerImage(){
        return playerImage;
    }
}
