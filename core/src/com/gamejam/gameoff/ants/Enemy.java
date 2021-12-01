package com.gamejam.gameoff.ants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Enemy {
    final Rectangle enemyHitBox;
    final Texture enemyImage;
    private Vector2 direction;

    public Enemy(float a, float b, int variant){
        enemyHitBox = new Rectangle();
        enemyHitBox.width = 128;
        enemyHitBox.height = 128;
        enemyHitBox.x = 128*a + 128*b + 128 - enemyHitBox.width/2 -3840;
        enemyHitBox.y = -64*a + 64*b + 64 - enemyHitBox.height/2;
        enemyImage = new Texture(Gdx.files.internal("enemy_"+variant+".png"));
        direction = newDirection();
    }

    public Rectangle getEnemyHitBox() { return enemyHitBox; }

    public Texture getEnemyImage() { return enemyImage; }

    public void moveEnemy(MapManager map){
        //cuadrante 0
        if(direction.x >= 0 && direction.y >= 0){
            if(map.isNotOutOfLimits(enemyHitBox,0) && map.isNotOutOfLimits(enemyHitBox,1) && map.isNotOutOfLimits(enemyHitBox,2) && map.isNotOutOfLimits(enemyHitBox,3)){
                //move
                enemyHitBox.x += direction.x*30*Gdx.graphics.getDeltaTime();
                enemyHitBox.y += direction.y*30*Gdx.graphics.getDeltaTime();
                // if(after move map.isoutoflimits regresar movimiento)
            }else{
                System.out.println("new direction 0");
                direction = newDirection(true, true);
                enemyHitBox.x += direction.x*100*Gdx.graphics.getDeltaTime();
                enemyHitBox.y += direction.y*100*Gdx.graphics.getDeltaTime();
            }
        }
        //cuadrante 1
        else if(direction.x <= 0 && direction.y >= 0){
            if(map.isNotOutOfLimits(enemyHitBox,0) && map.isNotOutOfLimits(enemyHitBox,1) && map.isNotOutOfLimits(enemyHitBox,2) && map.isNotOutOfLimits(enemyHitBox,3)){
                //move
                enemyHitBox.x += direction.x*30*Gdx.graphics.getDeltaTime();
                enemyHitBox.y += direction.y*30*Gdx.graphics.getDeltaTime();
            }else{
                System.out.println("new direction 1");
                direction = newDirection(false, true);
                enemyHitBox.x += direction.x*100*Gdx.graphics.getDeltaTime();
                enemyHitBox.y += direction.y*100*Gdx.graphics.getDeltaTime();
            }
        }
        //cuadrante 2
        else if(direction.x <= 0 && direction.y <= 0){
            if(map.isNotOutOfLimits(enemyHitBox,0) && map.isNotOutOfLimits(enemyHitBox,1) && map.isNotOutOfLimits(enemyHitBox,2) && map.isNotOutOfLimits(enemyHitBox,3)){
                //move
                enemyHitBox.x += direction.x*30*Gdx.graphics.getDeltaTime();
                enemyHitBox.y += direction.y*30*Gdx.graphics.getDeltaTime();
            }else{
                System.out.println("new direction 2");
                direction = newDirection(false,false);
                enemyHitBox.x += direction.x*100*Gdx.graphics.getDeltaTime();
                enemyHitBox.y += direction.y*100*Gdx.graphics.getDeltaTime();
            }
        }
        //cuadrante 3
        else if(direction.x >= 0 && direction.y <= 0){
            if(map.isNotOutOfLimits(enemyHitBox,0) && map.isNotOutOfLimits(enemyHitBox,1) && map.isNotOutOfLimits(enemyHitBox,2) && map.isNotOutOfLimits(enemyHitBox,3)){
                //move
                enemyHitBox.x += direction.x*30*Gdx.graphics.getDeltaTime();
                enemyHitBox.y += direction.y*30*Gdx.graphics.getDeltaTime();
            }else{
                System.out.println("new direction 3");
                direction = newDirection(true, false);
                enemyHitBox.x += direction.x*100*Gdx.graphics.getDeltaTime();
                enemyHitBox.y += direction.y*100*Gdx.graphics.getDeltaTime();
            }
        }
    }

    private Vector2 newDirection(){
        Random random = new Random();
        int x = (int)(random.nextFloat()*10);
        int y = (int)(random.nextFloat()*10);
        if(random.nextBoolean()) {//true x = -x
            x = -x;
        }
        if(random.nextBoolean()){//true y = -y
            y = -y;
        }
        return new Vector2(x,y);
    }

    private Vector2 newDirection(boolean xsign, boolean ysign){
        Random random = new Random();
        int x = (int)(random.nextFloat()*10);
        int y = (int)(random.nextFloat()*10);
        if(xsign) {//true x positive
            x = -x;
        }
        if(ysign){//true y positive
            y = -y;
        }
        return new Vector2(x,y);
    }
}
