package com.gamejam.gameoff.ants;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class AntsManager {
    Array<Ant> ants;
    Array<Texture> antsImages;
    Array<Rectangle> antsRectangles;
    int numberOfAnts = 5;

    public AntsManager(){
        ants = new Array<Ant>();
        antsImages = new Array<Texture>();
        antsRectangles = new Array<Rectangle>();
        spawnAnts();
    }

    private void spawnAnts(){
        int n = numberOfAnts;
        Random random = new Random();
        while(n>0){
            Ant newAnt = new Ant(random.nextInt(1000),random.nextInt(500));
            ants.add(newAnt);
            n--;
        }
    }

    public void drawAnts(GameManager gameManager){
        int n = 0;
        while(n<ants.size){
            gameManager.batch.draw(
                    ants.get(n).getAntImage(),
                    ants.get(n).getAntHitBox().x,
                    ants.get(n).getAntHitBox().y,
                    ants.get(n).getAntHitBox().getWidth(),
                    ants.get(n).getAntHitBox().getHeight()
            );
            n++;
        }
    }
}
