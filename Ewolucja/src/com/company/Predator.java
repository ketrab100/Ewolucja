package com.company;

public class Predator extends Animal {
    int strenght;

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

    void hunt(int hunter){
        int target;
        int targetX;
        int targetY;
        //tutaj trzeba zrobić przejście po listach zwierząt i znaleźć cel na polowanie
        {

        }
    }
}
