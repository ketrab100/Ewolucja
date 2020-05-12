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
    boolean hunger(){
        if(this.stomach<=this.maxStomach/2)
            return true;
        else
            return false;
    }

    void eatFood() {
        if (this.target.isInRange == 1) {
            this.stomach += this.target.value;
            this.stomach = Math.max(this.stomach, this.maxStomach);
            this.positionY = this.target.positionY;
            this.positionX = this.target.positionX;
        }
    }
    void moveToFood(){
        int movementLeft = this.speed;

        movementLeft=this.speed;
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
    void searchFruit(List<Fruit> listofFruits){ //to chyba trzeba przerobić na wskaźnik na listę
        int bestof=-1;
        int bestoflist=0;
        int isInRange=0;
        int calories=0;

        for(int q=0; q<listofFruits.size(); q++){
            Fruit food = listofFruits.get(q);
            if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=this.searchRange){
                if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=this.speed){
                    if(food.value>calories){
                        bestof=q;
                        calories=food.value;
                        isInRange=1;
                    }
                }
                else if(isInRange==0)
                    if(food.value>calories){
                        bestof=q;
                        calories=food.value;
                    }
            }
        }
        if(bestof==-1){
            this.target.numberOnTheList =bestof;
        }
        else {
            this.target.isInRange = isInRange;
            this.target.numberOnTheList = bestof;
            this.target.typeOf = bestoflist;
            Fruit _food = listofFruits.get(bestof);
            this.target.positionX = _food.positionX;
            this.target.positionY = _food.positionY;
            this.target.value = _food.value;
        }
    }
    void searchPrey(List<Animal> listofPreys) {
        int bestof = -1;
        int bestoflist = -1;
        int calories = 0;
        int isInRange = 0;

        for (int q = 0; q < listofPreys.size(); q++) {
            Animal prey = listofPreys.get(q);

            if (this.strenght > prey.resistance) {
                if (Math.abs(prey.positionX - this.positionX) + Math.abs(prey.positionY - this.positionY) <= this.searchRange) {
                    if (Math.abs(prey.positionX - this.positionX) + Math.abs(prey.positionY - this.positionY) <= this.speed) {
                        if (prey.value > calories) {
                            bestof = q;
                            bestoflist = 1;
                            calories = prey.value;
                            isInRange = 1;
                        }
                    } else if (isInRange == 0) {
                        if (prey.value > calories) {
                            bestof = q;
                            bestoflist = 1;
                            calories = prey.value;
                        }
                    }
                }
            }
        }
    }
}
