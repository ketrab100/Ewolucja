package com.company;

import java.util.Vector;

public class Tiger extends Predator {

    public Tiger(int positonX, int positonY) {
        super(positonX, positonY);
        this.speed = 5;
        this.age=0;
        this.maxDelivery=50;
        this.maxStomach=100;
        this.stomach=100;
    }
    Tiger lilTiger(){
        Tiger lil = new Tiger(this.positonX,this.positonY);
        return lil;
    }

}
