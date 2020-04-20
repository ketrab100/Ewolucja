package com.company;

import java.util.Random;


 public abstract class Animal implements Cloneable {
    int positonX;
    int positonY;
    int speed;
    int age;
    int maxStomach;
    int stomach;
    int delivery;
    int maxDelivery;

    public Animal(int positonX, int positonY){
        this.positonX=positonX;
        this.positonY=positonY;
    }
    public Object clone() {
        Object newObject=null;
        try { newObject = super.clone(); } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newObject;
    }
    void move (){
        Random r = new Random();
        if (Math.random()>0.5) {
            this.positonX+=r.nextInt(speed);
        }
        if (Math.random()>0.5) {
            this.positonY+=r.nextInt(speed);
        }
        if (Math.random()<0.5) {
            this.positonX-=r.nextInt(speed);
        }
        if (Math.random()<0.5) {
            this.positonY-=r.nextInt(speed);
        }
    }
    void growth(int time){
        age+=time;
    }

    abstract Animal makeChild(){

    }
    void readyToDelivery(){

    }
}
