package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    final Rectangle playerHitBox;
    final Texture playerImage;
    private boolean isCarrying;
    private boolean isAntBehind;
    private Ant antBehind;

    public Player() {
        playerHitBox = new Rectangle();
        playerHitBox.width = 128;
        playerHitBox.height = 64;
        playerHitBox.x =0 ;
        playerHitBox.y = 0;
        playerImage = new Texture(Gdx.files.internal("player.png"));
        isCarrying = false;
        isAntBehind = false;
    }

    public Rectangle getPlayerHitBox() {
        return playerHitBox;
    }

    public Texture getPlayerImage(){
        return playerImage;
    }

    public void addAnt(){
        isAntBehind = true;
        antBehind = new Ant(playerHitBox.x-playerHitBox.width,playerHitBox.y);
        antBehind.setAntImage("antM.png");
    }

    public boolean isCarrying(){
        return isCarrying;
    }

    public void setCarrying(){
        isCarrying = true;
    }

    public boolean getAntBehind(){ return isAntBehind; }

    public void drawAntFollowingPlayer(GameManager gameManager){
        gameManager.batch.draw(
                antBehind.getAntImage(),
                antBehind.getAntHitBox().x,
                antBehind.getAntHitBox().y,
                antBehind.getAntHitBox().getWidth(),
                antBehind.getAntHitBox().getHeight()
        );
    }

    public float getAntBehindX() { return antBehind.getAntHitBox().x; }

    public float getAntBehindY() {return antBehind.getAntHitBox().y; }

    public void moveAntFollowingPlayer(float moveAmount, boolean axisX){
        if(axisX){
            antBehind.antHitBox.x += moveAmount;
        }else{
            antBehind.antHitBox.y += moveAmount;
        }
    }
}
