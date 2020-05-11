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
    void searchFood(List<Predator> listofPredators, List<Herbivore> listofHerbivores, List<Human> listofPeople, int iterator, int idCheckTab[]){
        int bestof=-1;
        int bestoflist=-1;
        int calories=0;
        int isInRange=0;
        int movementLeft;

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
        for(int q=0; q<listofPeople.size(); q++){
            Human prey = listofPeople.get(q);

            if(this.strenght>prey.resistance){
                if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchrange){
                    if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchrange){
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
            this.moveRandom();
            return;
        }
        //nie wiem dlaczzego się podświetla
        if(bestoflist==1) {
            Predator food = listofPredators.get(bestof);
        }
        else if(bestoflist==2) {
            Herbivore food = listofHerbivores.get(bestof);
        }
        else{
            Human food = listofPeople.get(bestof);
        }

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
                movementLeft-=Math.min(movementLeft, Math.abs(food.positionX-this.positionX));
                this.positionX-=speed+movementLeft;
                if(movementLeft!=0){
                    if(food.positionY>this.positionY)
                        this.positionY+=movementLeft;
                    else
                        this.positionY-=movementLeft;
                }
            }
        }
        idCheckTab[food.id]=0;

        //to jest źle

        if(bestoflist==1) {
            listofPredators.remove(bestof);

            if(iterator<bestof) iterator--;
        }
        else if(bestoflist==2)
            listofHerbivores.remove(bestof);

        else listofPeople.remove(bestof);
    }
}
