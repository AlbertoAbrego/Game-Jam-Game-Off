package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class HUD {
    final Texture energyBarImage;
    final Rectangle energyBarRectangle;
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
        energyBarRectangle.height = 500;
        energyBarRectangle.x = -650f;
        energyBarRectangle.y = -300f;
        energyBarImage = new Texture(Gdx.files.internal("energyBar.png"));
        antCounterIconRectangle = new Rectangle();
        antCounterIconRectangle.width = 128;
        antCounterIconRectangle.height = 128;
        antCounterIconRectangle.x = -670f;
        antCounterIconRectangle.y = 190f;
        antCounterIcon = new Texture(Gdx.files.internal("antIcon128.png"));
        antCounter = 0;
        aCTx = -640;
        aCTy = 360;
        antCounterText = "x"+ antCounter;
        scoreText = "Puntaje:";
        scoreNumber = 0;
        sTx = -640;
        sTy = -310;
    }

    public Rectangle getEnergyBarRectangle(){ return energyBarRectangle; }

    public Texture getEnergyBarImage(){
        return energyBarImage;
    }

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

    public void moveHUD(float moveAmount, boolean axisX){
        if(axisX){
            antCounterIconRectangle.x += moveAmount;
            energyBarRectangle.x += moveAmount;
            setaCTx(moveAmount);
            setsTx(moveAmount);
        }else{
            antCounterIconRectangle.y += moveAmount;
            energyBarRectangle.y += moveAmount;
            setaCTy(moveAmount);
            setsTy(moveAmount);
        }
    }

    public void incrementScore(int pointsToAdd){
        scoreNumber += pointsToAdd;
    }
}
