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
        int test=0;
        int cal=0;

        for(int q=0; q<listofFruits.size(); q++){
            Fruit food = listofFruits.get(q);
            if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=searchrange){
                if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=speed){
                    if(food.value>cal){
                        bestof=q;
                        cal=food.value;
                        test=1;
                    }
                }
                else if(test==0)
                    if(food.value>cal){
                        bestof=q;
                        cal=food.value;
                    }
            }
        }
        if(bestof==-1) {
            this.moveRandom();
            return;
        }

        Fruit food = listofFruits.get(bestof); // może być źle

        if(test==1){
            this.stomach+=food.value;
            this.stomach=Math.max(this.stomach, this.maxStomach);
            this.positionY=food.positionY;
            this.positionX=food.positionX;
        }
        else{
            test=this.speed;
            if(food.positionX>this.positionX){
                test-=Math.min(test, Math.abs(food.positionX-this.positionX));
                this.positionX+=speed-test;
                if(test!=0){
                    if(food.positionY>this.positionY)
                        this.positionY+=test;
                    else
                        this.positionY-=test;
                }
            }
            else{
                test-=Math.min(test, Math.abs(food.positionX-this.positionX));
                this.positionX-=speed+test;
                if(test!=0){
                    if(food.positionY>this.positionY)
                        this.positionY+=test;
                    else
                        this.positionY-=test;
                }
            }
        }
        listofFruits.remove(bestof);
    }
}




























