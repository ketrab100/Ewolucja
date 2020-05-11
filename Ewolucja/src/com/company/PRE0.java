package com.company;

import java.util.Vector;

public class PRE0 extends Predator {

    public PRE0(int id, int positonX, int positonY) {
        super(positonX, positonY);
        this.name="TIGER";
        this.speed = 5;
        this.age=0;
        this.maxDelivery=50;
        this.maxStomach=100;
        this.stomach=100;
    }
    PRE0 lilTiger(){
        PRE0 lil = new PRE0(this.positionX,this.positionY);
        return lil;
    }

}
