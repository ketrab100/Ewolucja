package com.company;

public class Predator extends Animal {


    public Predator(int positonX, int positonY) {
        super(positonX, positonY);
    }

    void makeChild() {
        clone();
    }

    /*
    Herbivore findPrey (){

    }

     */
    boolean canEat(Herbivore _herbivore){
    return true;
    }
}
