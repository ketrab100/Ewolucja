package com.company;

import java.util.Vector;

public class PRE1 extends Predator {

    public PRE1(int id, int positonX, int positonY) {
        super(id, positonX, positonY);
        this.name="WOLF       ";
        this.value=10;
        this.searchRange=15;
        this.speed = 10;
        this.age=0;
        this.delivery=0;
        this.maxDelivery=20;
        this.maxStomach=25;
        this.stomach=25;
        this.strenght=20;
        this.resistance=21;
    }

}
