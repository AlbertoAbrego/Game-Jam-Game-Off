package com.gamejam.gameoff.ants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class SpawnedInMap {
    private Array<Vector2> ThingsInMapPositions;

    public SpawnedInMap(){
        ThingsInMapPositions = new Array<Vector2>();
        ThingsInMapPositions.add(new Vector2(15,15));
    }

    public void addPositions(int x, int y){
        Vector2 newPosition = new Vector2(x,y);
        ThingsInMapPositions.add(newPosition);
    }

    public boolean isAvailable(int x, int y){
        Vector2 newPosition = new Vector2(x,y);
        if(!ThingsInMapPositions.contains(newPosition,false)){
            addPositions(x,y);
            return true;
        }
        return false;
    }

    public void printPositions(){
        Iterator<Vector2> iterator = ThingsInMapPositions.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().x+" "+iterator.next().y);
        }
    }

    public void removePosition(Vector2 positionToRemove){
        ThingsInMapPositions.removeValue(positionToRemove,true);
    }
}
