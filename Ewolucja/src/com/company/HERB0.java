package com.company;

import java.util.Vector;

public class HERB0 extends Herbivore {

    public HERB0(int id, int positonX, int positonY) {
        super(id, positonX, positonY);
        this.value=12;
        this.searchRange=8;
        this.name="GOAT ";
        this.speed = 5;
        this.age=0;
        this.delivery=0;
        this.maxDelivery=20;
        this.maxStomach=25;
        this.stomach=25;
        this.resistance=10;
    }
}
