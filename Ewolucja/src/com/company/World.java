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
    List<Fruit> listofFruits = new ArrayList<Fruit>();

    {
        Random rand = new Random();
        {
            listofPredator.add(new PRE0(100, rand.nextInt(sizeX), rand.nextInt(sizeY)));
            listofPredator.add(new PRE1(200, rand.nextInt(sizeX), rand.nextInt(sizeY))); //i tak dalej
        }
        {
            listofHerbivore.add(new HERB0(1100, rand.nextInt(sizeX), rand.nextInt(sizeY))); //i tak dalej
        }

        {
            listofPeople.add(new Human(0,rand.nextInt(sizeX),rand.nextInt(sizeY)));
        }
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
    void herbivoreActivities (){
        for(int q=0; q<listofHerbivore.size(); q++){
            Herbivore act = listofHerbivore.get(q);
            act.stomach--;

            if(act.stomach==0) {//nie wiem, czy to jest na pewno dobrze
                listofHerbivore.remove(q);
                q--;
            }
            else {
                if (act.readyToDelivery()) act.makeChild();

                else if (act.hunger())
                    act.searchFood(listofFruits, act);

                else
                    act.moveRandom();

                if (act.hunger() != true)
                    act.delivery++;

                act.delivery++;
                act.age++;
                listofHerbivore.set(q, act);
            }
        }
    }

    void predatorActivities (){
        for (int q=0; q<listofPredator.size(); q++){
            Predator act = listofPredator.get(q);

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