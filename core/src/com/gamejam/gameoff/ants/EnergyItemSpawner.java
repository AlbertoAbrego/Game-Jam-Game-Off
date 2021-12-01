package com.gamejam.gameoff.ants;

import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class EnergyItemSpawner {
    Array<EnergyItem> energyItems;
    int numberOfItems = 20;

    public EnergyItemSpawner(SpawnedInMap spawnedInMap){
        energyItems = new Array<EnergyItem>();
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
            EnergyItem newItem = new EnergyItem(x,y,random.nextInt(5));
            energyItems.add(newItem);
            n--;
        }
    }

    public void drawItems(GameManager gameManager){
        int n = 0;
        while(n<energyItems.size){
            gameManager.batch.draw(
                    energyItems.get(n).getEnergyItemImage(),
                    energyItems.get(n).getEnergyItemHitBox().x,
                    energyItems.get(n).getEnergyItemHitBox().y,
                    energyItems.get(n).getEnergyItemHitBox().getWidth(),
                    energyItems.get(n).getEnergyItemHitBox().getHeight()
            );
            n++;
        }
    }
}
