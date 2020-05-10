package com.company;

import java.util.List;

public class Herbivore extends Animal {

    public Herbivore(int positonX, int positonY) {
        super(positonX, positonY);
    }

    void makeChild() {

    }
    //nie wiem jak działa java, ale tu trzeba przekazywać wskaźniki, potem poprawię
    void searchFood(List<Fruit> listofFruits, Herbivore act){ //to chyba trzeba przerobić na wskaźnik na listę
        int bestof=-1;
        int test=0;
        int cal=0;

        for(int q=0; q<listofFruits.size(); q++){
            Fruit food = listofFruits.get(q);
            if(Math.abs(food.positionX-act.positionX)+Math.abs(food.positionY-act.positionY)<=searchrange){
                if(Math.abs(food.positionX-act.positionX)+Math.abs(food.positionY-act.positionY)<=speed){
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
            act.moveRandom();
            return;
        }

        Fruit food = listofFruits.get(bestof);

        if(test==1){
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
        listofFruits.remove(bestof);
    }
}




























