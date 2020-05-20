package com.company;

import java.util.List;

public class Herbivore extends Animal {

    public Herbivore() {

    }

    Herbivore makeChild(int[] idCheckTab){
        this.delivery=0;
        this.stomach*=0.75;
        int newbornID=this.id%100;
        while(idCheckTab[newbornID]!=0 && newbornID<240000) {
            newbornID+=100;
        }
        Herbivore child =(Herbivore) this.clone();
        child.age=0;
        child.id=newbornID;
        idCheckTab[newbornID]=1;
        return child;
    }
    void searchFood(List<Fruit> listofFruits){
        this.target.numberOnTheList=-1;
        this.target.typeOf=-1;
        this.target.isInRange=0;
        this.target.value=0;
        this.target.id=99;

        this.searchFruit(listofFruits);
    }
    void increaseStats() {
        if (!this.isHungry())
            this.delivery++;

        this.delivery++;
        this.age++;
    }
}




























