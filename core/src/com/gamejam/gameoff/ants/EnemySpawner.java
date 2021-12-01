package com.gamejam.gameoff.ants;

import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class EnemySpawner {
    Array<Enemy> enemies;
    int numberOfEnemies = 5;

    public EnemySpawner(){
        enemies = new Array<Enemy>();
        spawnEnemies();
    }

    private void spawnEnemies(){
        int n = numberOfEnemies;
        Random random = new Random();
        while(n>0){
            Enemy newEnemy = new Enemy(random.nextInt(28)+1, random.nextInt(28)+1,random.nextInt(2));
            enemies.add(newEnemy);
            n--;
        }
    }

    public void drawEnemies(GameManager gameManager){
        int n = 0;
        while(n<enemies.size){
            gameManager.batch.draw(
                    enemies.get(n).getEnemyImage(),
                    enemies.get(n).getEnemyHitBox().x,
                    enemies.get(n).getEnemyHitBox().y,
                    enemies.get(n).getEnemyHitBox().getWidth(),
                    enemies.get(n).getEnemyHitBox().getHeight()
            );
            n++;
        }
    }

    public void moveEnemies(MapManager map){
        int n = 0;
        while(n<enemies.size){
            enemies.get(n).moveEnemy(map);
            n++;
        }
    }
}
