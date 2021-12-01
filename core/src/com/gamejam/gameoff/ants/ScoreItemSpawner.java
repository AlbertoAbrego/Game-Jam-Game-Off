package com.gamejam.gameoff.ants;

import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class ScoreItemSpawner {
    Array<ScoreItem> scoreItems;
    int numberOfItems = 30;

    public ScoreItemSpawner(SpawnedInMap spawnedInMap){
        scoreItems = new Array<ScoreItem>();
        spawnItems(spawnedInMap);
    }

    private void spawnItems(SpawnedInMap spawnedInMap){
        int n = numberOfItems;
        Random random = new Random();
        while(n>0){
            int x = random.nextInt(30);
            int y = random.nextInt(30);
            while(!spawnedInMap.isAvailable(x,y)){
                x = random.nextInt(30);
                y = random.nextInt(30);
            }
            ScoreItem newItem = new ScoreItem(x, y, random.nextInt(3)+1);
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
