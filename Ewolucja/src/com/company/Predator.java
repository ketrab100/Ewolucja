package com.company;

import java.util.List;

public class Predator extends Animal {

    public Predator(int id, int positonX, int positonY){
        super(id, positonX, positonY);
        this.target.numberOnTheList=-1;
    }

    Predator makeChild(int[] idCheckTab){
        this.delivery=0;
        int newbornID=this.id%100;
        while(idCheckTab[newbornID]!=0 && newbornID<240000) {
            newbornID+=100;
        }
        Predator child = (Predator) this.clone();
        child.id=newbornID;
        idCheckTab[newbornID]=1;
        return child;
    }
    void searchFood(List<Animal> listofPredators, List<Animal> listofHerbivores, List<Animal> listofPeople){
        this.target.numberOnTheList=-1;
        this.target.typeOf=-1;
        this.target.isInRange=0;
        this.target.value=0;
        this.target.id=99;

        this.searchPrey(listofPredators, 1);
        this.searchPrey(listofHerbivores, 2);
        this.searchPrey(listofPeople, 3);
    }
}
