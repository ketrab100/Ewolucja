package com.company;

import java.util.Random;


public abstract class Animal implements Cloneable {
    String name;
    int id;
    int positionX;
    int positionY;
    int speed;
    int age;
    int maxStomach;
    int stomach;
    int delivery;
    int maxDelivery;
    int range;
    int value;

    public Animal(int positionX, int positionY){
        this.positionX=positionX;
        this.positionY=positionY;
    }
    public Object clone() {
        Object newObject=null;
        try { newObject = super.clone(); } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newObject;
    }
    void moveRandom (){
        Random rand=new Random();

        int directionY=rand.nextInt(speed*2+1)-speed;
        int help=speed-Math.abs(directionY);

        int directionX=rand.nextInt(help*2+1)-help;

        this.positionX=directionX;
        this.positionY=directionY;
    }

    boolean readyToDelivery(){
        if (this.age+1%maxDelivery==0){
            return true;
        }
        else {
            return false;
        }
    }
    boolean hunger(int id){
        if(this.stomach<=maxStomach/2)
            return true;
        else
            return false;
    }
}
