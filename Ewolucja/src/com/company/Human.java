package com.company;

import java.util.List;

public class Human extends Animal {
    int level;

    public Human() {
        this.target.numberOnTheList=-1;
        this.value=15;
        this.searchRange=15;
        this.speed = 5;
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
        this.stomach*=0.75;
        int newbornID=0;
        while(idCheckTab[newbornID]!=0 && newbornID<240000){
            newbornID+=100;
        }
        Human child = (Human) this.clone();
        child.id=newbornID;
        child.level=0;
        idCheckTab[newbornID]=1;
        return child;
    }
    void searchFood(List<Fruit> listofFruits, List<Predator> listofPredators, List<Herbivore> listofHerbivores){
        this.target.numberOnTheList=-1;
        this.target.typeOf=-1;
        this.target.isInRange=0;
        this.target.value=0;
        this.target.id=99;

        this.searchFruit(listofFruits);
        //this.searchPrey(listofPredators, 1);
        //this.searchPrey(listofHerbivores, 2);
    }
}
