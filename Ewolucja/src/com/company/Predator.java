package com.company;

import java.util.List;

public class Predator extends Animal {
    int strenght;

    public Predator(int id, int positonX, int positonY){
        super(id, positonX, positonY);
        this.target.numberOnTheList=-1;
    }

    Predator makeChild(int[] idCheckTab){
        this.delivery=0;
        int newbornID=this.id%100;
        while(idCheckTab[newbornID]!=0 && newbornID<240000) {
            newbornID+=100;
        }
        Predator child = (Predator) this.clone();
        child.id=newbornID;
        idCheckTab[newbornID]=1;
        return child;
    }
    void searchFood(List<Predator> listofPredators, List<Herbivore> listofHerbivores, List<Human> listofPeople){
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
        for(int q=0; q<listofPeople.size(); q++){
            Human prey = listofPeople.get(q);

            if(this.strenght>prey.resistance){
                if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=this.searchRange){
                    if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=this.speed){
                        if(prey.value>calories){
                            bestof=q;
                            bestoflist=3;
                            calories= prey.value;
                            isInRange=1;
                        }
                    }
                    else if(isInRange==0){
                        if(prey.value>calories){
                            bestof=q;
                            bestoflist=3;
                            calories= prey.value;
                        }
                    }
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
                Human _food = listofPeople.get(bestof);
                this.target.positionX = _food.positionX;
                this.target.positionY = _food.positionY;
                this.target.value = _food.value;
                this.target.id= _food.id;
            }
        }
    }
}
