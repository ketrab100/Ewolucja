package com.company;

import java.util.List;

public class Predator extends Animal {
    private int maxStomach;
    private int speed;
    private int maxDelivery;
    private int resistance;
    private int strenght;
    private int searchRange;

    public Predator(int _value, int _searchRange, int _speed , int _maxDelivery, int _maxStomach , int _resistance, int _strength ){
        value = _value;
        searchRange = _searchRange;
        speed = _speed;
        maxDelivery = _maxDelivery;
        maxStomach  =_maxStomach;
        resistance = _resistance;
        strenght = _strength;
        this.target.numberOnTheList=-1;
    }

    Predator makeChild(int[] idCheckTab){
        this.delivery=0;
        this.stomach*=0.75;
        int newbornID=this.id%100;
        while(idCheckTab[newbornID]!=0 && newbornID<2400000) {
            newbornID+=100;
        }
        Predator child = (Predator) this.clone();
        child.age=0;
        child.id=newbornID;
        idCheckTab[newbornID]=1;
        return child;
    }
    void searchFood(List<Predator> listofPredators, List<Herbivore> listofHerbivores, List<Human> listofPeople){
        this.target.numberOnTheList=-1;
        this.target.typeOf=-1;
        this.target.isInRange=0;
        this.target.value=0;
        this.target.id=99;

        this.searchPrey(listofPredators, 1);
        this.searchPrey(listofHerbivores, 2);
        this.searchPrey(listofPeople, 3);
    }
    void increaseStats() {
        if (!this.isHungry())
            this.delivery++;

        this.age++;
    }
    boolean haveItMovedThisTurn(int q){
        if((this.target.id%100) > 0 && (this.target.id%100) <= 10 && q > this.target.numberOnTheList) return true;
        return false;
    }
}
