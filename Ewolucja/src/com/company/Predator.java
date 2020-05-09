package com.company;

import java.util.List;

public class Predator extends Animal {
    int strenght;

    public Predator(int positonX, int positonY) {
        super(positonX, positonY);
    }

    void makeChild() {
        clone();
    }

    void hunt(List<Predator> listofPredator, List<Herbivore> listofHerbivore, List<Human> listofPeople, Predator act){
        int bestof=-1;
        int bestoflist=-1;
        int cal=0;
        int test=0;

        for(int q=0; q<listofPredator.size(); q++){
            Predator prey = listofPredator.get(q);

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
        for(int q=0; q<listofHerbivore.size(); q++){
            Herbivore prey = listofHerbivore.get(q);

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
        for(int q=0; q<listofPeople.size(); q++){
            Human prey = listofPeople.get(q);

            if(act.strenght>prey.resistance){
                if(Math.abs(prey.positionX-act.positionX)+Math.abs(prey.positionY-act.positionY)<=searchrange){
                    if(Math.abs(prey.positionX-act.positionX)+Math.abs(prey.positionY-act.positionY)<=searchrange){
                        if(prey.value>cal){
                            bestof=q;
                            bestoflist=3;
                            cal= prey.value;
                            test=1;
                        }
                    }
                    else if(test==0){
                        if(prey.value>cal){
                            bestof=q;
                            bestoflist=3;
                            cal= prey.value;
                        }
                    }
                }
            }
        }
        if(bestof==-1) return;

        if(bestoflist==1)
            Predator food = listofPredator.get(bestof);

        else if(bestoflist==2)
            Herbivore food = listofHerbivore.get(bestof);

        else Human food = listofPeople.get(bestof);

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
            listofPredator.remove(bestof); //tu trzeba przekazać spowrotem i sprawdzić, czy już był jego ruch, ewentualnie przesunąć iterator

        else if(bestoflist==2)
            listofHerbivore.remove(bestof);

        else listofPeople.remove(bestof);
    }
}
