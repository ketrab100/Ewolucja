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
    public  PRE0(){
        this.name="Tiger";
        super();
    }
    public  void randomIni(){
        super.randomIni();
        //losowe
    }
    public String toString(){
        return this.getClass().getSimpleName();
    }

}
