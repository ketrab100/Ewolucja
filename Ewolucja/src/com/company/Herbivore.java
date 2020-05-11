package com.company;

import java.util.List;

public class Herbivore extends Animal {

    public Herbivore(int positonX, int positonY) {
        super(positonX, positonY);
    }

    void makeChild() {
        this.delivery=0;
        //clone();
    }
    //nie wiem jak działa java, ale tu trzeba przekazywać wskaźniki, potem poprawię
    void searchFood(List<Fruit> listofFruits){ //to chyba trzeba przerobić na wskaźnik na listę
        int bestof=-1;
        int isInRange=0;
        int calories=0;
        int movementLeft;

        for(int q=0; q<listofFruits.size(); q++){
            Fruit food = listofFruits.get(q);
            if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=searchrange){
                if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=speed){
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
        if(bestof==-1) {
            this.moveRandom();
            return;
        }

        Fruit food = listofFruits.get(bestof);

        if(isInRange==1){
            this.stomach+=food.value;
            this.stomach=Math.max(this.stomach, this.maxStomach);
            this.positionY=food.positionY;
            this.positionX=food.positionX;
        }
        else {
            movementLeft = this.speed;
            if (food.positionX > this.positionX) {
                movementLeft -= Math.min(movementLeft, Math.abs(food.positionX - this.positionX));
                this.positionX += speed - movementLeft;
                if (movementLeft != 0) {
                    if (food.positionY > this.positionY)
                        this.positionY += movementLeft;
                    else
                        this.positionY -= movementLeft;
                }
            } else {
                movementLeft -= Math.min(movementLeft, Math.abs(food.positionX - this.positionX));
                this.positionX -= speed + movementLeft;
                if (movementLeft != 0) {
                    if (food.positionY > this.positionY)
                        this.positionY += movementLeft;
                    else
                        this.positionY -= movementLeft;
                }
            }
        }

        //to jest źle

        listofFruits.remove(bestof);
    }
}




























