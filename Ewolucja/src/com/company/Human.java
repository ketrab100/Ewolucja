package com.company;

import java.util.List;

public class Human extends Animal {
    int strenght;
    int level;

    public Human(int id, int strenght, int positonX, int positonY) {
        super(id, positonX, positonY);
        this.target.numberOnTheList=-1;
        this.value=15;
        this.searchRange=5;
        this.strenght=strenght;
        this.name="HUMAN      ";
        this.speed = 3;
        this.age=0;
        this.level=0;
        this.delivery=0;
        this.maxDelivery=20;
        this.maxStomach=25;
        this.stomach=25;
        this.resistance=2;
    }
    Human makeChild(int[] idCheckTab){
        this.delivery=0;
        int newbornID=0;
        while(idCheckTab[newbornID]!=0 && newbornID<240000){
            newbornID+=100;
        }
        Human child = new Human(newbornID, this.strenght, this.positionX, this.positionY);
        idCheckTab[newbornID]=1;
        return child;
    }
    /*
    void searchFood(List<Predator> listofPredators, List<Herbivore> listofHerbivores, List<Fruit> listofFruits){
        int bestof=-1;
        int bestoflist=-1;
        int calories=0;
        int isInRange=0;

        for(int q=0; q<listofPredators.size(); q++){
            Predator prey = listofPredators.get(q);

            if(this.strenght>prey.resistance){
                if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=this.searchRange){
                    if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=this.speed){
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
                if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=this.searchRange){
                    if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=this.speed){
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
            if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=this.searchRange){
                if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=this.speed){
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
                        bestoflist=0;
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
    }
     */
}
