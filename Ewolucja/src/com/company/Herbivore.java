package com.company;

import java.util.List;

public class Herbivore extends Animal {

    public Herbivore(int id, int positonX, int positonY) {
        super(id, positonX, positonY);
    }

    Herbivore makeChild(int[] idCheckTab){
        this.delivery=0;
        int newbornID=this.id%100;
        while(idCheckTab[newbornID]!=0 && newbornID<240000) {
            newbornID+=100;
        }
        Herbivore child =(Herbivore) this.clone();
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
}




























