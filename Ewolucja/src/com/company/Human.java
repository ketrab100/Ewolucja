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
        int act=0;
        while(idCheckTab[act]!=0)
            act++;

        listofPeople.add(new Human(act, this.strenght, this.positionX, this.positionY));
        idCheckTab[act]=1;
        //reszta statystyk i tak jest identyczna, level i wiek się zerują z tego co mi wiadomo
    }
    //nie wiem jak działa java, ale tu trzeba przekazywać wskaźniki, potem poprawię
    void searchFood(List<Predator> listofPredators, List<Herbivore> listofHerbivores, List<Fruit> listofFruits, int idCheckTab[]){
        int bestof=-1;
        int bestoflist=-1;
        int cal=0;
        int test=0;

        for(int q=0; q<listofPredators.size(); q++){
            Predator prey = listofPredators.get(q);

            if(this.strenght>prey.resistance){
                if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchrange){
                    if(Math.abs(prey.positionX-this.positionX)+Math.abs(prey.positionY-this.positionY)<=searchrange){
                        if(prey.value>cal){
                            bestof=q;
                            bestoflist=1;
                            cal= prey.value;
                            test=1;
                        }
                    }
                    else if(test==0){
                        if(prey.value>cal){
                            bestof=q;
                            bestoflist=1;
                            cal= prey.value;
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
                        if(prey.value>cal){
                            bestof=q;
                            bestoflist=2;
                            cal= prey.value;
                            test=1;
                        }
                    }
                    else if(test==0){
                        if(prey.value>cal){
                            bestof=q;
                            bestoflist=2;
                            cal= prey.value;
                        }
                    }
                }
            }
        }
        for(int q=0; q<listofFruits.size(); q++){
            Fruit food = listofFruits.get(q);
            if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=searchrange){
                if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=speed){
                    if(food.value>cal){
                        bestof=q;
                        bestoflist=3;
                        cal=food.value;
                        test=1;
                    }
                }
                else if(test==0)
                    if(food.value>cal){
                        bestof=q;
                        bestoflist=3;
                        cal=food.value;
                    }
            }
        }

        if(bestof==-1){
            this.moveRandom();
            return;
        }
        //nie wiem dlaczzego się podświetla
        if(bestoflist==1)
            Predator food =  listofPredators.get(bestof);

        else if(bestoflist==2)
            Herbivore food = listofHerbivores.get(bestof);

        else Fruit food = listofFruits.get(bestof);

        if(test==1){ //tu się wyświetla żarcie na czerwono, ale nie ma błędu, bo jeśli się nie przerwie to food na pewno zostanie stworzony, napisałem do gulowatego z tym
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
        idCheckTab[food.id]=0;

        if(bestoflist==1)
            listofPredators.remove(bestof);

        else if(bestoflist==2)
            listofHerbivores.remove(bestof);

        else listofFruits.remove(bestof);
    }
}
