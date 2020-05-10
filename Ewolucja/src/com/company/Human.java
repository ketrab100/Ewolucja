package com.company;

import java.util.List;
import java.util.Vector;

public class Human extends Animal {
    int strenght;
    int level;

    public Human(int id, int strenght, int positonX, int positonY) {
        super(positonX, positonY);
        this.name="GOAT ";
        this.speed = 5;
        this.age=0;
        this.maxDelivery=50;
        this.maxStomach=100;
        this.stomach=100;
    }
    void makeChild(){

    }
    //nie wiem jak działa java, ale tu trzeba przekazywać wskaźniki, potem poprawię
    void searchFood(List<Predator> listofPredators, List<Herbivore> listofHerbivores, List<Fruit> listofFruits, Human act){
        int bestof=-1;
        int bestoflist=-1;
        int cal=0;
        int test=0;

        for(int q=0; q<listofPredators.size(); q++){
            Predator prey = listofPredators.get(q);

            if(act.strenght>prey.resistance){
                if(Math.abs(prey.positionX-act.positionX)+Math.abs(prey.positionY-act.positionY)<=searchrange){
                    if(Math.abs(prey.positionX-act.positionX)+Math.abs(prey.positionY-act.positionY)<=searchrange){
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

            if(act.strenght>prey.resistance){
                if(Math.abs(prey.positionX-act.positionX)+Math.abs(prey.positionY-act.positionY)<=searchrange){
                    if(Math.abs(prey.positionX-act.positionX)+Math.abs(prey.positionY-act.positionY)<=searchrange){
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
            if(Math.abs(food.positionX-act.positionX)+Math.abs(food.positionY-act.positionY)<=searchrange){
                if(Math.abs(food.positionX-act.positionX)+Math.abs(food.positionY-act.positionY)<=speed){
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
            act.moveRandom();
            return;
        }
        //nie wiem dlaczzego się podświetla
        if(bestoflist==1)
            Predator food = listofPredators.get(bestof);

        else if(bestoflist==2)
            Herbivore food = listofHerbivores.get(bestof);

        else Fruit food = listofFruits.get(bestof);

        if(test==1){ //tu się wyświetla żarcie na czerwono, ale nie ma błędu, bo jeśli się nie przerwie to food na pewno zostanie stworzony
            act.stomach+=food.value;
            act.stomach=Math.max(act.stomach, act.maxStomach);
            act.positionY=food.positionY;
            act.positionX=food.positionX;
        }
        else{
            test=act.speed;
            if(food.positionX>act.positionX){
                test-=Math.min(test, Math.abs(food.positionX-act.positionX));
                act.positionX+=speed-test;
                if(test!=0){
                    if(food.positionY>act.positionY)
                        act.positionY+=test;
                    else
                        act.positionY-=test;
                }
            }
            else{
                test-=Math.min(test, Math.abs(food.positionX-act.positionX));
                act.positionX-=speed+test;
                if(test!=0){
                    if(food.positionY>act.positionY)
                        act.positionY+=test;
                    else
                        act.positionY-=test;
                }
            }
        }
        if(bestoflist==1)
            listofPredators.remove(bestof);

        else if(bestoflist==2)
            listofHerbivores.remove(bestof);

        else listofFruits.remove(bestof);
    }
}
