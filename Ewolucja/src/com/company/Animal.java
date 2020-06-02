package com.company;

import java.util.List;
import java.util.Random;


public class Animal implements Cloneable {
    String name;
    int strenght;
    int id;
    int positionX;
    int positionY;
    int speed;
    int age=0;
    protected int maxStomach;
    int stomach;
    int delivery;
    protected int maxDelivery;
    int searchRange;
    int value;
    int resistance;
    Target target = new Target();

    public Animal(){
    }

    void randomInitialization(int x, int y){
        Random random = new Random();
        this.positionX=random.nextInt(x)+1;
        this.positionY=random.nextInt(y)+1;
    }

    public Object clone() {
        Object newObject=null;
        try { newObject = super.clone(); } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newObject;
    }

    void moveRandom (int sizeX, int sizeY){
        Random rand=new Random();

        int help = (rand.nextInt(this.speed*2+1)-this.speed);
        this.positionX+=help;
        help=this.speed-Math.abs(help);
        if(this.positionX<=0){
            help+=Math.abs(this.positionX)+1;
            this.positionX=1;
        }
        else if(this.positionX>sizeX){
            help=help-sizeX+this.positionX;
            this.positionX=sizeX;
        }
        this.positionY+=(rand.nextInt(help*2+1)-help);

        if(this.positionY<=0) this.positionY=1;
        else if(this.positionY>sizeY) this.positionY=sizeY;
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
                    }
                    else if (this.target.isInRange == 0) {
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
    public String classNameToString(){
        return this.getClass().getSimpleName();
    }

}
