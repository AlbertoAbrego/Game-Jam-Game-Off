package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Ant {
    final Rectangle antHitBox;
    final Texture antImage;
    private boolean isAntBehind;
    private Ant antBehind;

    public Ant(float x, float y) {
        antHitBox = new Rectangle();
        antHitBox.width = 128;
        antHitBox.height = 64;
        antHitBox.x = x;
        antHitBox.y = y;
        antImage = new Texture(Gdx.files.internal("antIcon128.png"));
        isAntBehind = false;
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

    public boolean hasAntBehind(){ return isAntBehind; }

    public void addAnt(){
        if(isAntBehind){
            antBehind.addAnt();
        }else{
            isAntBehind = true;
            antBehind = new Ant(antHitBox.x- antHitBox.width,antHitBox.y);
            System.out.println(antHitBox.x- antHitBox.width+" "+antHitBox.y);
            antBehind.setAntImage("antM.png");
        }
    }

    public float getAntBehindX() { return antBehind.getAntHitBox().x; }

    public float getAntBehindY() {return antBehind.getAntHitBox().y; }

    public void drawAntFollowingAnt(GameManager gameManager){
        gameManager.batch.draw(
                antBehind.getAntImage(),
                antBehind.getAntHitBox().x,
                antBehind.getAntHitBox().y,
                antBehind.getAntHitBox().getWidth(),
                antBehind.getAntHitBox().getHeight()
        );
        if(antBehind.hasAntBehind()){
            antBehind.drawAntFollowingAnt(gameManager);
        }
    }

    public void moveAntFollowingAnt(float moveAmount, boolean axisX){
        if(axisX){
            antBehind.antHitBox.x += moveAmount;
        }else{
            antBehind.antHitBox.y += moveAmount;
        }
        if(antBehind.hasAntBehind()){
            if(moveAmount>0 && !axisX){
                if(antBehind.getAntHitBox().y> antBehind.getAntBehindY()+64){
                    antBehind.moveAntFollowingAnt(moveAmount,axisX);
                }
            }
            if(moveAmount<0 && !axisX){
                if(antBehind.getAntHitBox().y< antBehind.getAntBehindY()-64){
                    antBehind.moveAntFollowingAnt(moveAmount,axisX);
                }
            }
            if(moveAmount<0 && axisX){
                if(antBehind.getAntHitBox().x< antBehind.getAntBehindX()-128){
                    antBehind.moveAntFollowingAnt(moveAmount,axisX);
                }
            }
            if(moveAmount>0 && axisX){
                if(antBehind.getAntHitBox().x> antBehind.getAntBehindX()+128){
                    antBehind.moveAntFollowingAnt(moveAmount,axisX);
                }
            }
        }
    }
}
