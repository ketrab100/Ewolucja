package com.company;

import java.util.List;

public class Human extends Animal {
    int level;

    public Human(int id, int strenght, int positonX, int positonY) {
        super(id, positonX, positonY);
        this.target.numberOnTheList=-1;
        this.value=15;
        this.searchRange=5;
        this.strenght=strenght;
        this.name="HUMAN      ";
        this.speed = 3;
        this.age=0;
        this.level=0;
        this.delivery=0;
        this.maxDelivery=20;
        this.maxStomach=25;
        this.stomach=25;
        this.resistance=2;
    }
    Human makeChild(int[] idCheckTab){
        this.delivery=0;
        int newbornID=0;
        while(idCheckTab[newbornID]!=0 && newbornID<240000){
            newbornID+=100;
        }
        Human child = new Human(newbornID, this.strenght, this.positionX, this.positionY);
        idCheckTab[newbornID]=1;
        return child;
    }
    void searchFood(List<Fruit> listofFruits, List<Animal> listofPredators, List<Animal> listofHerbivores){
        this.target.numberOnTheList=-1;
        this.target.typeOf=-1;
        this.target.isInRange=0;
        this.target.value=0;
        this.target.id=99;

        this.searchFruit(listofFruits);
        this.searchPrey(listofPredators, 1);
        this.searchPrey(listofHerbivores, 2);
    }
}
