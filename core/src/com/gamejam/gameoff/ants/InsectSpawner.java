package com.gamejam.gameoff.ants;

import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class InsectSpawner {
    Array<Insect> insects;
    int numberOfInsects = 10;

    public InsectSpawner(SpawnedInMap spawnedInMap){
        insects = new Array<Insect>();
        spawnInsects(spawnedInMap);
    }

    private void spawnInsects(SpawnedInMap spawnedInMap){
        int n = numberOfInsects;
        Random random = new Random();
        while(n>0){
            int x = random.nextInt(30);
            int y = random.nextInt(30);
            while(!spawnedInMap.isAvailable(x,y)){
                x = random.nextInt(30);
                y = random.nextInt(30);
            }
            Insect newInsect = new Insect(x,y,random.nextInt(3), random.nextBoolean());
            insects.add(newInsect);
            n--;
        }
    }

    public void drawInsects(GameManager gameManager){
        int n = 0;
        while(n<insects.size){
            gameManager.batch.draw(
                    insects.get(n).getInsectImage(),
                    insects.get(n).getInsectHitBox().x,
                    insects.get(n).getInsectHitBox().y,
                    insects.get(n).getInsectHitBox().getWidth(),
                    insects.get(n).getInsectHitBox().getHeight()
            );
            n++;
        }
    }

    public void moveInsects(MapManager map){
        int n = 0;
        while(n<insects.size){
            insects.get(n).moveInsect(map);
            n++;
        }
    }
}
