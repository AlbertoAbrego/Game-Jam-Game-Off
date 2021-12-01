package com.gamejam.gameoff.ants;

import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class AntsSpawner {
    Array<Ant> ants;
    int numberOfAnts = 7;

    public AntsSpawner(SpawnedInMap spawnedInMap){
        ants = new Array<Ant>();
        spawnAnts(spawnedInMap);
    }

    private void spawnAnts(SpawnedInMap spawnedInMap){
        int n = numberOfAnts;
        Random random = new Random();
        while(n>0){
            int x = random.nextInt(30);
            int y = random.nextInt(30);
            while(!spawnedInMap.isAvailable(x,y)){
                x = random.nextInt(30);
                y = random.nextInt(30);
            }
            Ant newAnt = new Ant(x,y);
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
