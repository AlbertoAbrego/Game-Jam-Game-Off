package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class HUD {
    final Texture energyBarImage;
    final Rectangle energyBarRectangle;
    final Texture energyImage;
    final Rectangle energyRectangle;
    final Texture antCounterIcon;
    final Rectangle antCounterIconRectangle;
    private CharSequence antCounterText;
    private float aCTx, aCTy, sTx, sTy;
    private int antCounter;
    final CharSequence scoreText;
    private int scoreNumber;

    public HUD() {
        energyBarRectangle = new Rectangle();
        energyBarRectangle.width = 100;
        energyBarRectangle.height = 490;
        energyBarRectangle.x = -525f;
        energyBarRectangle.y = -236f;
        energyBarImage = new Texture(Gdx.files.internal("energyBar.png"));
        energyRectangle = new Rectangle();
        energyRectangle.width = 100;
        energyRectangle.height = 480;
        energyRectangle.x = -525f;
        energyRectangle.y = -231f;
        energyImage = new Texture(Gdx.files.internal("energy.png"));
        antCounterIconRectangle = new Rectangle();
        antCounterIconRectangle.width = 128;
        antCounterIconRectangle.height = 128;
        antCounterIconRectangle.x = -525f;
        antCounterIconRectangle.y = 254f;
        antCounterIcon = new Texture(Gdx.files.internal("antIcon128.png"));
        antCounter = 1;
        aCTx = -525;
        aCTy = 424;
        antCounterText = "x"+ antCounter;
        scoreText = "Puntaje:";
        scoreNumber = 0;
        sTx = -525;
        sTy = -246;
    }

    public Rectangle getEnergyBarRectangle(){ return energyBarRectangle; }

    public Texture getEnergyBarImage(){
        return energyBarImage;
    }

    public Rectangle getEnergyRectangle() { return energyRectangle; }

    public Texture getEnergyImage() { return energyImage; }

    public Rectangle getAntCounterRectangle(){
        return antCounterIconRectangle;
    }

    public Texture getAntCounterIcon(){
        return antCounterIcon;
    }

    public CharSequence getAntCounterText(){ return antCounterText; }

    public CharSequence getScore(){ return scoreText + " " + scoreNumber; }

    public float getaCTx() { return aCTx; }

    public void setaCTx(float xchange){ aCTx +=xchange; }

    public float getaCTy() { return aCTy; }

    public void setaCTy(float ychange){
        aCTy += ychange;
    }

    public float getsTx() { return sTx; }

    public void setsTx(float xchange) { sTx += xchange; }

    public float getsTy() { return sTy; }

    public void setsTy(float ychange) { sTy += ychange; }

    public void addAntCounter(){
        antCounter++;
        antCounterText = "x"+antCounter;
    }

    public int getAntCounter() { return antCounter; }

    public void updateEnergy(float amount){
        energyRectangle.height = amount * 16;
    }

    public void moveHUD(float moveAmount, boolean axisX){
        if(axisX){
            antCounterIconRectangle.x += moveAmount;
            energyBarRectangle.x += moveAmount;
            energyRectangle.x += moveAmount;
            setaCTx(moveAmount);
            setsTx(moveAmount);
        }else{
            antCounterIconRectangle.y += moveAmount;
            energyBarRectangle.y += moveAmount;
            energyRectangle.y += moveAmount;
            setaCTy(moveAmount);
            setsTy(moveAmount);
        }
    }

    public void updateScore(int currentScore){ scoreNumber = currentScore; }
}
