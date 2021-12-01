package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Insect {
    final Rectangle insectHitBox;
    final Texture insectImage;
    final Vector2 position;
    private int points;
    private int antsRequired;
    final float xStart;
    final float yStart;
    private boolean updownOrleftright;
    private boolean upOrDown;
    private boolean leftOrRight;

    public Insect(float a, float b, int level, boolean udOrlr){
        position = new Vector2(a,b);
        insectHitBox = new Rectangle();
        insectHitBox.width = 128;
        insectHitBox.height = 64;
        insectHitBox.x = 128*a + 128*b + 128 - insectHitBox.width/2 -3840;
        insectHitBox.y = -64*a + 64*b + 64 - insectHitBox.height/2;
        xStart = insectHitBox.x;
        yStart = insectHitBox.y;
        insectImage = new Texture(Gdx.files.internal("insect_"+level+".png"));
        switch (level){
            case 0: points = 55; antsRequired = 4; break;
            case 1: points = 85; antsRequired = 6; break;
            case 2: points = 150; antsRequired = 8; break;
        }
        updownOrleftright = udOrlr;//true=up|down false=left|rigth
        upOrDown = true;//true=up false=down
        leftOrRight = true;//true=left false=right
    }

    public Vector2 getPosition() { return position; }

    public Rectangle getInsectHitBox() { return insectHitBox; }

    public Texture getInsectImage() { return insectImage; }

    public int getPoints() { return points; }

    public int getAntsRequired() { return antsRequired; }

    public void moveInsect(MapManager map){
        if(updownOrleftright){
            if(upOrDown && map.isNotOutOfLimits(insectHitBox,0) && map.isNotOutOfLimits(insectHitBox,1)){//up
                insectHitBox.y += 50*Gdx.graphics.getDeltaTime();
                if(insectHitBox.y > yStart+150){
                    upOrDown = false;
                }
            }else{
                upOrDown = false;
            }
            if(!upOrDown && map.isNotOutOfLimits(insectHitBox,2) && map.isNotOutOfLimits(insectHitBox,3)){//down
                insectHitBox.y -= 50*Gdx.graphics.getDeltaTime();
                if(insectHitBox.y < yStart-150){
                    upOrDown = true;
                }
            }else{
                upOrDown = true;
            }
        }else{
            if(leftOrRight && map.isNotOutOfLimits(insectHitBox,0) && map.isNotOutOfLimits(insectHitBox,3)){//left
                insectHitBox.x -= 100*Gdx.graphics.getDeltaTime();
                if(insectHitBox.x < xStart-300){
                    leftOrRight = false;
                }
            }else{
                leftOrRight = false;
            }
            if (!leftOrRight && map.isNotOutOfLimits(insectHitBox,1) && map.isNotOutOfLimits(insectHitBox,2)){//right
                insectHitBox.x += 100*Gdx.graphics.getDeltaTime();
                if(insectHitBox.x > xStart+300){
                    leftOrRight = true;
                }
            }
            else{
                leftOrRight = true;
            }
        }
    }
}
