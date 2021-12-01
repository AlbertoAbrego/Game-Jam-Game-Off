package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ant {
    final Rectangle antHitBox;
    final Texture antImage;
    private boolean isAntBehind;
    private Ant antBehind;
    private boolean isCarrying;
    private Vector2 position;

    public Ant(float a, float b) {
        position = new Vector2(a,b);
        antHitBox = new Rectangle();
        antHitBox.width = 128;
        antHitBox.height = 64;
        antHitBox.x = 128*a + 128*b + 128 - antHitBox.width/2 -3840;
        antHitBox.y = -64*a + 64*b + 64 - antHitBox.height/2;
        antImage = new Texture(Gdx.files.internal("ant.png"));
        isAntBehind = false;
    }

    public Ant(float x, float y, String path) {
        antHitBox = new Rectangle();
        antHitBox.width = 128;
        antHitBox.height = 64;
        antHitBox.x = x;
        antHitBox.y = y;
        antImage = new Texture(Gdx.files.internal(path));
        isAntBehind = false;
    }

    public Vector2 getPosition() { return position; }

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
            antBehind = new Ant(antHitBox.x- antHitBox.width,antHitBox.y,"ant.png");
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

    public void setCarrying(){
        isCarrying = true;
        setAntImage("antcarrying.png");
    }

    public void setNotCarrying() {
        isCarrying = false;
        setAntImage("ant.png");
        if(hasAntBehind()){
            antBehind.setNotCarrying();
        }
    }

    public int canCarry() {
        int numberOfAntsCanCarry = 0;
        if(!isCarrying){
            numberOfAntsCanCarry++;
        }
        if(hasAntBehind()){
            numberOfAntsCanCarry += antBehind.canCarry();
        }
        return numberOfAntsCanCarry;
    }

    public void addItem(int antsRequired){
        if(antsRequired==1){
            if(!isCarrying){
                setCarrying();
            }else{
                antBehind.addItem(antsRequired);
            }
        }else{
            if(!isCarrying){
                setCarrying();
                antBehind.addItem(antsRequired-1);
            }else{
                antBehind.addItem(antsRequired);
            }
        }
    }
}
