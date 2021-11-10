package com.gamejam.gameoff.ants;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class ScoreItemManager {
    Array<ScoreItem> scoreItems;
    Array<Texture> scoreItemsImages;
    Array<Rectangle> scoreItemsRectangles;
    int numberOfItems = 5;

    public ScoreItemManager(){
        scoreItems = new Array<ScoreItem>();
        scoreItemsImages = new Array<Texture>();
        scoreItemsRectangles = new Array<Rectangle>();
        spawnItems();
    }

    private void spawnItems(){
        int n = numberOfItems;
        Random random = new Random();
        while(n>0){
            ScoreItem newItem = new ScoreItem(random.nextInt(1000), random.nextInt(500));
            scoreItems.add(newItem);
            n--;
        }
    }

    public void drawItems(GameManager gameManager){
        int n = 0;
        while(n<scoreItems.size){
            gameManager.batch.draw(
                    scoreItems.get(n).getScoreItemImage(),
                    scoreItems.get(n).getScoreItemHitBox().x,
                    scoreItems.get(n).getScoreItemHitBox().y,
                    scoreItems.get(n).getScoreItemHitBox().getWidth(),
                    scoreItems.get(n).getScoreItemHitBox().getHeight()
            );
            n++;
        }
    }
}
