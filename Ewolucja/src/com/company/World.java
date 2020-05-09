package com.company;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class World {
    int sizeX;
    int sizeY;
    PrintStream outStream;

    List<Predator> listofPredator = new ArrayList<Predator>();
    List<Herbivore> listofHerbivore = new ArrayList<Herbivore>();
    List<Human> listofPeople = new ArrayList<Human>();

    {
        Random rand=new Random();
        listofPredator.add(new PRE0(100,rand.nextInt(sizeX),rand.nextInt(sizeY)));
        listofPredator.add(new PRE1(200,rand.nextInt(sizeX),rand.nextInt(sizeY))); //i tak dalej
    }

    {
        Random rand=new Random();
        listofHerbivore.add(new HERB0(1100,rand.nextInt(sizeX),rand.nextInt(sizeY))); //i tak dalej
    }
    public World(int sizeX,int sizeY, PrintStream _outStream){
        this.outStream= _outStream;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }
    void day(){

    }
    void play(){
        day();
    }
    void predatorActivities (List<Predator> animal){
        for (int i=0;i<animal.size();i++){
            Predator predator;
            predator= (Predator) animal.get(i);

            if(predator.readyToDelivery()){
                predator.makeChild();
            }

            if(predator.hunger(predator.id)){ //ID albo coś, nie wiem jak to działa do końca skakanie po klasach
                predator.hunt(predator.id); //ID trzeba podać dalej

            }

            //outStream.println("pozycja x: " + predator.positionX);
            //outStream.println("pozycja y: " + predator.positionY);
        }
    }
    public void spawnFruits(List fruits) {
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
    void beginGame(){

    }
}