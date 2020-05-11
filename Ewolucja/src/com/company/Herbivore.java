package com.company;

import java.util.List;

public class Herbivore extends Animal {

    public Herbivore(int positonX, int positonY) {
        super(positonX, positonY);
    }

    Herbivore makeChild(int idCheckTab[]){ //napiszę jak można rozmnażać ludzi bez użycia tego clone(); zobacz, czy ci się podoba to dodam do reszty
        this.delivery=0;
        int newbornID=this.id-(this.id/100)*100;
        while(idCheckTab[newbornID]!=0) {
            newbornID+=100;
        }
        Herbivore child =(Herbivore) this.clone();
        child.id=newbornID;
        idCheckTab[newbornID]=1;
        return child;
    }
    //nie wiem jak działa java, ale tu trzeba przekazywać wskaźniki, potem poprawię
    void searchFood(List<Fruit> listofFruits){ //to chyba trzeba przerobić na wskaźnik na listę
        int bestof=-1;
        int bestoflist=0;
        int isInRange=0;
        int calories=0;
        int movementLeft;

        for(int q=0; q<listofFruits.size(); q++){
            Fruit food = listofFruits.get(q);
            if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=searchRange){
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
}




























