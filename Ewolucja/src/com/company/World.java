package com.company;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    int sizeX;
    int sizeY;
    int weather=3;
    int quantity;
    int currentTurn=100;
    int[] idCheckTab= new int[250000];
    int[][] statistics = new int[50][250000];
    int[] names = new int [50]; //to ma być tablica stringów z nazwami zwierząt
    {
        for (int q = 0; q < 250000; q++) {
            idCheckTab[q] = 0;
        }
    }
    PrintStream outStream;

    List<Predator> listofPredators = new ArrayList<Predator>();
    List<Herbivore> listofHerbivores = new ArrayList<Herbivore>();
    List<Human> listofPeople = new ArrayList<Human>();
    List<Fruit> listofFruits = new ArrayList<Fruit>();

    void beginGame(int animalQuantity, int humanQuantity, int humanStrenght){
        Random rand = new Random();
        for(int q=0; q<animalQuantity; q++){
            listofPredators.add(new PRE0(1+q*100, rand.nextInt(sizeX)+1, rand.nextInt(sizeY)+1));
            idCheckTab[1000+q]=1;
            listofPredators.add(new PRE1(2+q*100, rand.nextInt(sizeX)+1, rand.nextInt(sizeY)+1)); //i tak dalej
            idCheckTab[2000+q]=1;
        }
        for(int q=0; q<animalQuantity; q++){
            listofHerbivores.add(new HERB0(11+q*100, rand.nextInt(sizeX)+1, rand.nextInt(sizeY)+1)); //i tak dalej
            idCheckTab[11000+q]=1;
        }
        for(int q=0; q<humanQuantity; q++){
            listofPeople.add(new Human(0+q*100, humanStrenght, rand.nextInt(sizeX)+1, rand.nextInt(sizeY)+1));
            idCheckTab[q]=1;
        }
        this.spawnFruits();
    }

    public World(int sizeX,int sizeY, PrintStream _outStream){
        this.outStream= _outStream;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.quantity=sizeX/10+sizeY/10;
    }
    void turn() throws InterruptedException {
        this.systemOut();
        Thread.sleep(5000);
        Random rand = new Random();
        weather=rand.nextInt(5);
        //pogoda 0-chmury, słaby wzrost | 1-przelotne chmury, trochę słabszy wzrost | 2-czyste niebo, normalnie
        //3-słoneczny dzień, lepszy wzrost | 4-upał, brak wzrostu | 5-susza, rośliny umierają

        this.humanActivities();
        this.herbivoreActivities();
        this.predatorActivities();
        currentTurn++;
    }
    void play() throws InterruptedException {
        while(!listofPeople.isEmpty())
            turn();
    }
    void herbivoreActivities (){
        for(int q=0; q<listofHerbivores.size(); q++){
            Herbivore act = listofHerbivores.get(q);
            statistics[act.id%100][currentTurn]++;
            act.stomach--;

            if(act.stomach==0) {//nie wiem, czy to jest na pewno dobrze
                listofHerbivores.remove(q);
                q--;
            }
            else {
                if (act.readyToDelivery())  listofHerbivores.add(act.makeChild(idCheckTab));

                else if (act.hunger()) {
                    act.searchFood(listofFruits);

                    if(act.target.numberOnTheList ==-1){
                        act.moveRandom();
                    }
                    else{
                        act.eatFood();
                        if(act.target.isInRange==1)
                            this.deleteTarget(act.target);
                    }
                }
                else
                    act.moveRandom();

                if (!act.hunger())
                    act.delivery++;

                act.delivery++;
                act.age++;
                listofHerbivores.set(q, act);
            }
        }
    }

    void predatorActivities (){
        for (int q=0; q<listofPredators.size(); q++){
            Predator act = listofPredators.get(q);
            statistics[act.id%100][currentTurn]++;
            act.stomach--;

            if(act.stomach==0) {
                listofPredators.remove(q);
                q--;
            }
            else{
                if (act.readyToDelivery()) listofPredators.add(act.makeChild(idCheckTab));

                else if (act.hunger()) {
                    act.searchFood(listofPredators, listofHerbivores, listofPeople);

                    if(act.target.numberOnTheList ==-1){
                        act.moveRandom();
                    }
                    else{
                        act.eatFood();
                        if(act.target.isInRange==1) {
                            this.deleteTarget(act.target);
                            
                            if ((act.id%100) > 0 && (act.id%100) <= 10 && q > act.target.numberOnTheList)
                                q--;
                        }
                    }
                }
                else
                    act.moveRandom();

                if (!act.hunger())
                    act.delivery++;

                act.age++;
                listofPredators.set(q, act);
            }
        }
    }

    void humanActivities (){
        statistics[0][currentTurn]=listofPeople.size();
        for(int q=0; q<listofPeople.size(); q++){
            Human act= listofPeople.get(q);
            act.stomach--;

            if(act.stomach==0) {//nie wiem, czy to jest na pewno dobrze
                listofPeople.remove(q);
                q--;
            }
            else{
                if (act.readyToDelivery()) listofPeople.add(act.makeChild(idCheckTab));

                else if (act.hunger()) {
                    act.searchFood(listofPredators, listofHerbivores, listofFruits);

                    if(act.target.numberOnTheList ==-1){
                        act.moveRandom();
                    }
                    else{
                        act.eatFood();
                        if(act.target.isInRange==1)
                            this.deleteTarget(act.target);
                    }
                }
                else
                    act.moveRandom();

                if (!act.hunger())
                    act.delivery++;

                act.delivery++;
                act.age++;
                act.level++;

                if(act.age%10==0){
                    act.strenght++;
                }
                if(act.level%20==0){
                    act.level=0;
                    act.resistance++;
                }
                listofPeople.set(q, act);
            }
        }
    }

    void spawnFruits(){
        int act=quantity;

        if(weather==0) act*=0.5;
        else if(weather==1) act*=0.75;
        else if(weather==3) act*=1.5;
        else if(weather==4) return;
        else{
            act*=0.25;
            for(int q=0; q<act; q++){
                if(!listofFruits.isEmpty()){
                    listofFruits.remove(0);
                }
            }
            return;
        }
        for(int q=0; q<act; q++){
            Random rand = new Random();
            listofFruits.add(new Fruit(rand.nextInt(sizeX)+1,rand.nextInt(sizeY)+1, rand.nextInt(10)+1));
        }
    }
    void deleteTarget(Target target){
        idCheckTab[target.id]=0;

        if(target.typeOf ==0)
            listofFruits.remove(target.numberOnTheList);

        else if(target.typeOf ==1)
            listofPredators.remove(target.numberOnTheList);

        else if(target.typeOf ==2)
            listofHerbivores.remove(target.numberOnTheList);

        else if(target.typeOf ==3){
            listofPeople.remove(target.numberOnTheList);
        }
    }
    void systemOut(){
        int fillUp=8;
        int divider=10;
        System.out.print("Animal type | ");
        while((currentTurn-100)/divider>=10){
            divider++;
            fillUp--;
        }
        System.out.print(currentTurn-100); for(int w=0; w<fillUp; w++) System.out.print(" ");

        System.out.print(" | Last turn | 10 turns  | 20 turns  | 50 turns  | 100 turns  | At beginning \n");
        for(int q=0; q<=20; q++){
            System.out.print("Animal name | ");
            fillUp=8;
            divider=10;
            while((statistics[q][currentTurn])/divider>=10){
                divider++;
                fillUp--;
            }
            System.out.print(statistics[q][currentTurn]); for(int w=0; w<fillUp; w++) System.out.print(" "); System.out.print(" | ");

            fillUp=8;
            divider=10;
            while((statistics[q][currentTurn-1])/divider>=10){
                divider++;
                fillUp--;
            }
            System.out.print(statistics[q][currentTurn-1]); for(int w=0; w<fillUp; w++) System.out.print(" "); System.out.print(" | ");

            fillUp=8;
            divider=10;
            while((statistics[q][currentTurn-10])/divider>=10){
                divider++;
                fillUp--;
            }
            System.out.print(statistics[q][currentTurn-10]); for(int w=0; w<fillUp; w++) System.out.print(" "); System.out.print(" | ");

            fillUp=8;
            divider=10;
            while((statistics[q][currentTurn-20])/divider>=10){
                divider++;
                fillUp--;
            }
            System.out.print(statistics[q][currentTurn-20]); for(int w=0; w<fillUp; w++) System.out.print(" "); System.out.print(" | ");

            fillUp=8;
            divider=10;
            while((statistics[q][currentTurn-50])/divider>=10){
                divider++;
                fillUp--;
            }
            System.out.print(statistics[q][currentTurn-50]); for(int w=0; w<fillUp; w++) System.out.print(" "); System.out.print(" | ");

            fillUp=9;
            divider=10;
            while((statistics[q][currentTurn-100])/divider>=10){
                divider++;
                fillUp--;
            }
            System.out.print(statistics[q][currentTurn-100]); for(int w=0; w<fillUp; w++) System.out.print(" "); System.out.print(" | ");

            System.out.print(statistics[q][100]); for(int w=0; w<fillUp; w++) System.out.print(" ");

            System.out.print("\n");
        }
    }
}

























