package com.company;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class World {
    int sizeX;
    int sizeY;
    PrintStream outStream;
    List<Predator> tigers = new LinkedList<Predator>();
    Tiger tiger = new Tiger(0,0);
    public World(int sizeX,int sizeY, PrintStream _outStream){
        this.outStream= _outStream;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }
    void day(){
        tigers.add(tiger);
        outStream.println("dzień: ");
        predatorActivities(tigers);
    }
    void play(){
        day();
    }
    void predatorActivities (List<Predator> animal){
        for (int i=0;i<animal.size();i++){
            Predator predator;
            predator= (Predator) animal.get(i);
            predator.move();
            if(predator.readyToDelivery()){
                predator.makeChild();
            }
            outStream.println("pozycja x: " + predator.positonX);
            outStream.println("pozycja y: " + predator.positonY);
        }
    }

}