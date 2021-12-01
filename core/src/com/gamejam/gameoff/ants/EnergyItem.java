package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnergyItem {
    final Rectangle energyItemHitBox;
    final Texture energyItemImage;
    final Vector2 position;
    private int energyAmount = 0;

    public EnergyItem (float a, float b, int level){
        position = new Vector2(a,b);
        energyItemHitBox = new Rectangle();
        energyItemHitBox.width = 64;
        energyItemHitBox.height = 64;
        energyItemHitBox.x = 128*a + 128*b + 128 - energyItemHitBox.width/2 -3840;
        energyItemHitBox.y = -64*a + 64*b + 64 - energyItemHitBox.height/2;
        energyItemImage = new Texture(Gdx.files.internal("energy_"+level+".png"));
        switch(level){
            case 0: energyAmount = 15; break;
            case 1: energyAmount = 20; break;
            case 2: energyAmount = 30; break;
            case 3: energyAmount = 35; break;
            case 4: energyAmount = 50; break;
        }
    }

    public Vector2 getPosition() { return position; }

    public Rectangle getEnergyItemHitBox() { return energyItemHitBox; }

    public Texture getEnergyItemImage() { return energyItemImage; }

    public int getEnergyAmount() { return energyAmount; }


}
