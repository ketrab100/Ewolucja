package com.company;

import java.util.Vector;

public class HERB0 extends Herbivore {

    public HERB0(int id, int positonX, int positonY) {
        super(positonX, positonY);
        this.name="GOAT ";
        this.speed = 5;
        this.age=0;
        this.maxDelivery=50;
        this.maxStomach=100;
        this.stomach=100;
    }
}
