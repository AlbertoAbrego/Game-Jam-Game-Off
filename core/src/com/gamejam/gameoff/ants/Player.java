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
    private int score;
    private int points;
    private float energy;
    private float maxEnergy;
    final float energyLostByStep;
    private float energyLost;
    private int numberOfAnts;

    public Player() {
        playerHitBox = new Rectangle();
        playerHitBox.width = 128;
        playerHitBox.height = 64;
        playerHitBox.x = 128-playerHitBox.width/2;
        playerHitBox.y = 64-playerHitBox.height/2;
        playerImage = new Texture(Gdx.files.internal("player.png"));
        isCarrying = false;
        isAntBehind = false;
        energy = 30;
        maxEnergy = 30;
        energyLostByStep = 0.5f;
        energyLost = energyLostByStep;
        numberOfAnts = 1;
    }

    public Rectangle getPlayerHitBox() {
        return playerHitBox;
    }

    public Texture getPlayerImage(){
        return playerImage;
    }

    public void setPlayerImage(String path){
        playerImage.load(new Texture(Gdx.files.internal(path)).getTextureData());
    }

    public float getEnergy() { return energy; }

    public void updateEnergy(float amount){
        energy -= amount * energyLost;
        if(energy < 0){
            energy = 0;
        }
    }

    public void addEnergy(int amount){
        energy += amount;
        if(energy > maxEnergy){
            energy = maxEnergy;
        }
    }

    public void updateEnergyLostByStep(){
        energyLost = energyLostByStep * numberOfAnts;
    }

    public int getNumberOfAnts() { return numberOfAnts; }

    public void addScore(int amount) { score += amount; }

    public int getScore(){ return score; }

    public void addPoints(int amount) { points += amount; }

    public int getPoints() { return points; }

    public void resetPoints() {
        points = 0;
        isCarrying = false;
        setPlayerImage("player.png");
        if(hasAntBehind()){
            antBehind.setNotCarrying();
        }
    }

    public void addAnt(){
        numberOfAnts++;
        maxEnergy += 30;
        energy += 30;
        updateEnergyLostByStep();
        if(isAntBehind){
            antBehind.addAnt();
        }else{
            isAntBehind = true;
            antBehind = new Ant(playerHitBox.x-playerHitBox.width,playerHitBox.y,"ant.png");
        }
    }

    public int antsCanCarry(){
        int numberOfAntsCanCarry = 0;
        if(!isCarrying){
            numberOfAntsCanCarry++;
        }
        if(hasAntBehind()){
            numberOfAntsCanCarry += antBehind.canCarry();
        }
        return numberOfAntsCanCarry;
    }

    public void setCarrying(){
        isCarrying = true;
        setPlayerImage("playercarrying.png");
    }

    public void addItem(int antsRequired) {
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

    public boolean hasAntBehind(){ return isAntBehind; }

    public void drawAntFollowingPlayer(GameManager gameManager){
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

    public float getAntBehindX() { return antBehind.getAntHitBox().x; }

    public float getAntBehindY() {return antBehind.getAntHitBox().y; }

    public void moveAntFollowingPlayer(float moveAmount, boolean axisX){
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
