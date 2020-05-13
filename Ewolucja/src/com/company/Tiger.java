package com.company;

import java.util.Vector;

public class Tiger extends Predator {

    public Tiger(int id, int positonX, int positonY) {
        super(id, positonX, positonY);
        this.value=15;
        this.searchRange=20;
        this.speed = 8;
        this.age=0;
        this.delivery=0;
        this.maxDelivery=20;
        this.maxStomach=25;
        this.stomach=25;
        this.strenght=30;
        this.resistance=35;
    }
    public String classNameToString(){
        return this.getClass().getSimpleName();
    }

}
