package com.company;

import java.util.List;
import java.util.Random;


public abstract class Animal implements Cloneable {
    String name;
    int strenght;
    int id;
    int positionX;
    int positionY;
    int speed;
    int age;
    int maxStomach;
    int stomach;
    int delivery;
    int maxDelivery;
    int searchRange;
    int value;
    int resistance;
    Target target = new Target();

    public Animal(int id, int positionX, int positionY){
        this.positionX=positionX;
        this.positionY=positionY;
        this.id=id;
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

        if(directionY<1) directionY=1;
        else if(directionY>100) directionY=100;

        if(directionX<1) directionX=1;
        else if(directionX>100) directionX=100;
        this.positionX=directionX;
        this.positionY=directionY;
    }

    boolean readyToDelivery(){
        if (this.delivery>=this.maxDelivery){
            return true;
        }
        else {
            return false;
        }
    }
    boolean isHungry(){
        if(this.stomach<=this.maxStomach/2)
            return true;
        else
            return false;
    }

    void eatFood() {
        if (this.target.isInRange == 1) {
            this.stomach += this.target.value;
            this.stomach = Math.min(this.stomach, this.maxStomach);
            this.positionY = this.target.positionY;
            this.positionX = this.target.positionX;
        }
    }
    void moveToFood(){
        int movementLeft = this.speed;

        if(this.target.positionX>this.positionX){
            movementLeft-=Math.min(movementLeft, Math.abs(this.target.positionX-this.positionX));
            this.positionX+=this.speed-movementLeft;
        }
        else{
            this.target.isInRange-=Math.min(movementLeft, Math.abs(this.target.positionX-this.positionX));
            this.positionX-=this.speed+movementLeft;
        }
        if(movementLeft!=0){
            if(this.target.positionY>this.positionY)
                this.positionY+=movementLeft;
            else
                this.positionY-=movementLeft;
        }
    }
    void searchFruit(List<Fruit> listofFruits){
        /*
        this.target.numberOnTheList=-1;
        this.target.typeOf=-1;
        this.target.isInRange=0;
        this.target.value=0;
         */
        for(int q=0; q<listofFruits.size(); q++){
            Fruit food = listofFruits.get(q);
            if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=this.searchRange) {
                if (Math.abs(food.positionX - this.positionX) + Math.abs(food.positionY - this.positionY) <= this.speed) {
                    if (food.value > this.target.value) {
                        this.target.numberOnTheList = q;
                        this.target.typeOf = 0;
                        this.target.value = food.value;
                        this.target.isInRange = 1;
                    }
                }
                else if (this.target.isInRange == 0){
                    if (food.value > this.target.value) {
                        this.target.numberOnTheList = q;
                        this.target.typeOf = 0;
                        this.target.value = food.value;
                    }
                }
            }
        }
        if(this.target.typeOf==0) {
            this.target.id=99;
            this.target.positionX = listofFruits.get(this.target.numberOnTheList).positionX;
            this.target.positionY = listofFruits.get(this.target.numberOnTheList).positionY;
        }
    }
    void searchPrey(List<? extends Animal> listofPreys, int currentList) {

        for (int q = 0; q < listofPreys.size(); q++) {
            Animal prey = listofPreys.get(q);

            if (this.strenght > prey.resistance) {
                if (Math.abs(prey.positionX - this.positionX) + Math.abs(prey.positionY - this.positionY) <= this.searchRange) {
                    if (Math.abs(prey.positionX - this.positionX) + Math.abs(prey.positionY - this.positionY) <= this.speed) {
                        if (prey.value > this.target.value) {
                            this.target.numberOnTheList = q;
                            this.target.typeOf=currentList;
                            this.target.value = prey.value;
                            this.target.id = prey.id;
                            this.target.isInRange = 1;
                        }
                    } else if (this.target.isInRange == 0) {
                        if (prey.value > this.target.value) {
                            this.target.numberOnTheList = q;
                            this.target.typeOf=currentList;
                            this.target.value = prey.value;
                            this.target.id = prey.id;
                        }
                    }
                }
            }
        }
        if (this.target.typeOf==currentList) {
            this.target.positionX = listofPreys.get(this.target.numberOnTheList).positionX;
            this.target.positionY = listofPreys.get(this.target.numberOnTheList).positionY;
        }
    }
}
