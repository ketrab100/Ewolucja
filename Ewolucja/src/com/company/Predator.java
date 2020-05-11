package com.company;

import java.util.List;

public class Predator extends Animal {
    int strenght;

    public Predator(int positonX, int positonY) {
        super(positonX, positonY);
    }

    void makeChild(List<Predator> listofPredators, int idCheckTab[]){ //napiszę jak można rozmnażać ludzi bez użycia tego clone(); zobacz, czy ci się podoba to dodam do reszty
        this.delivery=0;
        int newbornID=this.id-(this.id/100)*100;
        while(idCheckTab[newbornID]!=0) {
            newbornID+=100;
        }

        listofPredators.add(new this.clone());
        listofPredators.get(listofPredators.size()-1).id=newbornID;
        idCheckTab[newbornID]=1;
    }

    //nie wiem jak działa java, ale tu trzeba przekazywać wskaźniki, potem poprawię
    void searchFood(List<Predator> listofPredators, List<Herbivore> listofHerbivores, List<Human> listofPeople){
        int bestof=-1;
        int bestoflist=-1;
        int calories=0;
        int isInRange=0;

        for(int q=0; q<listofPredators.size(); q++){
            Predator prey = listofPredators.get(q);

            if(this.strenght>prey.resistance){
                if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchRange){
                    if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchRange){
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
                if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchRange){
                    if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchRange){
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
                if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchRange){
                    if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchRange){
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
                Human _food = listofPeople.get(bestof);
                this.target.positionX = _food.positionX;
                this.target.positionY = _food.positionY;
                this.target.value = _food.value;
            }
        }
    }
}
