package com.company;

import java.util.List;
import java.util.Vector;

public class Human extends Animal {
    int strenght;
    int level;

    public Human(int id, int strenght, int positonX, int positonY) {
        super(positonX, positonY);
        this.name="HUMAN ";
        this.speed = 5;
        this.age=0;
        this.level=0;
        this.maxDelivery=50;
        this.maxStomach=100;
        this.stomach=100;
    }
    void makeChild(List<Human> listofPeople, int idCheckTab[]){ //napiszę jak można rozmnażać ludzi bez użycia tego clone(); zobacz, czy ci się podoba to dodam do reszty
        this.delivery=0;
        int newbornID=100;
        while(idCheckTab[newbornID]!=0) {
            newbornID+=100;
        }

        listofPeople.add(new Human(newbornID, this.strenght, this.positionX, this.positionY));
        idCheckTab[newbornID]=1;
    }
    //nie wiem jak działa java, ale tu trzeba przekazywać wskaźniki, potem poprawię
    void searchFood(List<Predator> listofPredators, List<Herbivore> listofHerbivores, List<Fruit> listofFruits){
        int bestof=-1;
        int bestoflist=-1;
        int calories=0;
        int isInRange=0;

        for(int q=0; q<listofPredators.size(); q++){
            Predator prey = listofPredators.get(q);

            if(this.strenght>prey.resistance){
                if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchrange){
                    if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchrange){
                        if(prey.value>calories){
                            bestof=q;
                            bestoflist=1;
                            calories= prey.value;
                            isInRange=1;
                        }
                    }
                    else if(isInRange==0){
                        if(prey.value>calories){
                            bestof=q;
                            bestoflist=1;
                            calories= prey.value;
                        }
                    }
                }
            }
        }
        for(int q=0; q<listofHerbivores.size(); q++){
            Herbivore prey = listofHerbivores.get(q);

            if(this.strenght>prey.resistance){
                if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchrange){
                    if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchrange){
                        if(prey.value>calories){
                            bestof=q;
                            bestoflist=2;
                            calories= prey.value;
                            isInRange=1;
                        }
                    }
                    else if(isInRange==0){
                        if(prey.value>calories){
                            bestof=q;
                            bestoflist=2;
                            calories= prey.value;
                        }
                    }
                }
            }
        }
        for(int q=0; q<listofFruits.size(); q++){
            Fruit food = listofFruits.get(q);
            if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=searchrange){
                if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=speed){
                    if(food.value>calories){
                        bestof=q;
                        bestoflist=0;
                        calories=food.value;
                        isInRange=1;
                    }
                }
                else if(isInRange==0)
                    if(food.value>calories){
                        bestof=q;
                        bestoflist=3;
                        calories=food.value;
                    }
            }
        }

        if(bestof==-1){
            this.target.iterator=bestof;
        }
        else {
            this.target.isInRange = isInRange;
            this.target.iterator = bestof;
            this.target.iteratorlist = bestoflist;

            if (bestoflist == 1) {
                Predator _food = listofPredators.get(bestof);
                this.target.positionX = _food.positionX;
                this.target.positionY = _food.positionY;
                this.target.value = _food.value;
                this.target.id = _food.id;
            } else if (bestoflist == 2) {
                Herbivore _food = listofHerbivores.get(bestof);
                this.target.positionX = _food.positionX;
                this.target.positionY = _food.positionY;
                this.target.value = _food.value;
                this.target.id = _food.id;
            } else {
                Fruit _food = listofFruits.get(bestof);
                this.target.positionX = _food.positionX;
                this.target.positionY = _food.positionY;
                this.target.value = _food.value;
            }
        }
        /*
        if(isInRange==1){ //tu się wyświetla żarcie na czerwono, ale nie ma błędu, bo jeśli się nie przerwie to food na pewno zostanie stworzony
            this.stomach+=food.value;
            this.stomach=Math.max(this.stomach, this.maxStomach);
            this.positionY=food.positionY;
            this.positionX=food.positionX;
        }
        else{
            movementLeft=this.speed;
            if(food.positionX>this.positionX){
                movementLeft-=Math.min(movementLeft, Math.abs(food.positionX-this.positionX));
                this.positionX+=speed-movementLeft;
                if(movementLeft!=0){
                    if(food.positionY>this.positionY)
                        this.positionY+=movementLeft;
                    else
                        this.positionY-=movementLeft;
                }
            }
            else{
                isInRange-=Math.min(movementLeft, Math.abs(food.positionX-this.positionX));
                this.positionX-=speed+movementLeft;
                if(movementLeft!=0){
                    if(food.positionY>this.positionY)
                        this.positionY+=movementLeft;
                    else
                        this.positionY-=movementLeft;
                }
            }
        }*/
    }
}
