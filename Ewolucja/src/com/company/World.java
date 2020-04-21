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
    List<Predator> wolfs = new LinkedList<>();
    Wolf wolf= new Wolf(0,0);
    List<Fruit> fruits = new LinkedList<>();


    public World(int sizeX,int sizeY, PrintStream _outStream){
        this.outStream= _outStream;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }
    void day(){
        tigers.add(tiger);
        wolfs.add(wolf);
        spawnFruits(fruits);
        outStream.println("dzień: ");
        System.out.println("tygrysy");
        predatorActivities(tigers);
        System.out.println("wilki");
        predatorActivities(wolfs);
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
    public  void spawnFruits(List fruits) {
        for (int i = 0; i < this.sizeX; i++) {
            for (int j = 0; j < this.sizeY; j++) {
                double random = Math.random();
                if (random >= 0.9) {
                    Fruit superFruit = new Fruit(i, j, 3);
                    fruits.add(superFruit);
                } else if (random > 0.7) {
                    Fruit berries = new Fruit(i, j, 2);
                    fruits.add(berries);
                } else if (random > 0.5) {

                    Fruit grass = new Fruit(i, j, 1);
                    fruits.add(grass);

                }
            }
        }
    }

}