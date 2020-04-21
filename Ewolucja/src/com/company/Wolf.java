package com.company;

public class Wolf extends Predator {

    public Wolf(int positonX, int positonY) {
        super(positonX, positonY);
        this.speed = 2;
        this.age=0;
        this.maxDelivery=20;
        this.maxStomach=50;
        this.stomach=50;
    }
}
