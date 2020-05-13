package com.company;

import java.util.Vector;

public class Goat extends Herbivore {

    public Goat(int id, int positonX, int positonY) {
        super(id, positonX, positonY);
        this.value=12;
        this.searchRange=20;
        this.name="GOAT ";
        this.speed = 6;
        this.age=0;
        this.delivery=0;
        this.maxDelivery=20;
        this.maxStomach=25;
        this.stomach=25;
        this.resistance=10;

    }
}
