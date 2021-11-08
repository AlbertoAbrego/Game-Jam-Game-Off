package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class HUD {
    private Texture energyBarImage;
    private Rectangle energyBar;
    private Texture antCounterIcon;
    private Rectangle antCounterIconRectangle;
    private CharSequence antCounterText;
    private float aCTx, aCTy;
    private int antCounter;

    public HUD() {
        energyBar = new Rectangle();
        energyBar.width = 100;
        energyBar.height = 500;
        energyBar.x = -650f;
        energyBar.y = -300f;
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
    }

    public Rectangle getEnergyBar(){ return energyBar; }

    public Texture getEnergyBarImage(){
        return energyBarImage;
    }

    public Rectangle getAntCounter(){
        return antCounterIconRectangle;
    }

    public Texture getAntCounterIcon(){
        return antCounterIcon;
    }

    public CharSequence getAntCounterText(){ return antCounterText; }

    public float getaCTx() { return aCTx; }

    public float getaCTy() { return aCTy; }

    public void setaCTx(float xchange){
        aCTx +=xchange;
    }
    public void setaCTy(float ychange){
        aCTy += ychange;
    }
    public void addAntCounter(){
        antCounter++;
        antCounterText = "x"+antCounter;
    }
}
