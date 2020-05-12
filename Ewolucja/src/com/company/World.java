package com.company;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    int sizeX;
    int sizeY;
    int quantity;
    int weather=3;
    int currentTurn=100;
    int[] idCheckTab= new int[250000];
    int[][] statistics = new int[50][250000];
    int[] names = new int [50]; //to ma być tablica stringów z nazwami zwierząt

    PrintStream outStream;

    List<Animal> listofPredators = new ArrayList<Animal>();
    List<Animal> listofHerbivores = new ArrayList<Animal>();
    List<Animal> listofPeople = new ArrayList<Animal>();
    List<Fruit> listofFruits = new ArrayList<Fruit>();

    void beginGame(int animalQuantity, int humanQuantity, int humanStrenght){
        Random rand = new Random();
        for(int q=0; q<animalQuantity; q++){
            this.listofPredators.add(new PRE0(1+q*100, rand.nextInt(sizeX)+1, rand.nextInt(sizeY)+1));
            this.idCheckTab[1+q*100]=1;
            this.statistics[1][100]=animalQuantity;
            this.listofPredators.add(new PRE1(2+q*100, rand.nextInt(sizeX)+1, rand.nextInt(sizeY)+1)); //i tak dalej
            this.idCheckTab[2+q*100]=1;
            this.statistics[2][100]=animalQuantity;
        }
        for(int q=0; q<animalQuantity; q++){
            this.listofHerbivores.add(new HERB0(11, rand.nextInt(sizeX)+1, rand.nextInt(sizeY)+1)); //i tak dalej
            this.idCheckTab[11+q*100]=1;
            this.statistics[11][100]=animalQuantity;
            //System.out.println("ja pierdole " + this.listofHerbivores.size() + " " + this.listofHerbivores.get(q).id + " " + this.listofHerbivores.get(q).positionY);
        }
        for(int q=0; q<humanQuantity; q++){
            this.listofPeople.add(new Human(0+q*100, humanStrenght, rand.nextInt(sizeX)+1, rand.nextInt(sizeY)+1));
            this.idCheckTab[0+q*100]=1;
            this.statistics[0][100]=humanQuantity;
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
        /* //do testowania statystyk
        for(int q=0; q<11; q++){
            for(int w=0; w<10; w++){
                System.out.print(this.statistics[q][w+100]);
            }
            System.out.println();
        }*/
        for(int q=0; q<250000; q++)
            if(idCheckTab[q]!=0)System.out.print(q + " ");

        this.systemOut();
        System.out.println(this.listofFruits.size() +  " " + this.listofPeople.size() + " " + this.listofPredators.size() + " " + this.listofHerbivores.size());
        Thread.sleep(2000);
        this.currentTurn++;
        Random rand = new Random();
        this.weather=rand.nextInt(6);
        //pogoda 0-chmury, słaby wzrost | 1-przelotne chmury, trochę słabszy wzrost | 2-czyste niebo, normalnie
        //3-słoneczny dzień, lepszy wzrost | 4-upał, brak wzrostu | 5-susza, rośliny umierają

        this.humanActivities();
        this.herbivoreActivities();
        this.predatorActivities();
        this.spawnFruits();
    }
    void play() throws InterruptedException {
        while(!this.listofPeople.isEmpty()) {
            this.turn();
            System.out.print("XD");
        }
    }
    void herbivoreActivities (){
        for(int q=0; q<this.listofHerbivores.size(); q++){
            Herbivore act = (Herbivore) this.listofHerbivores.get(q);
            this.statistics[act.id%100][currentTurn]++;
            act.stomach--;

            if(act.stomach==0) {//nie wiem, czy to jest na pewno dobrze
                this.listofHerbivores.remove(q);
                q--;
            }
            else {
                if (act.readyToDelivery())  this.listofHerbivores.add(act.makeChild(this.idCheckTab));

                else if (act.hunger()) {
                    act.searchFood(this.listofFruits);

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
                    act.delivery+=2;

                act.delivery++;
                act.age++;
                this.listofHerbivores.set(q, act);
            }
        }
    }

    void predatorActivities (){
        for (int q=0; q<this.listofPredators.size(); q++){
            Predator act = (Predator) this.listofPredators.get(q);
            this.statistics[act.id%100][currentTurn]++;
            act.stomach--;
            //System.out.print(act.id + " " + act.delivery + " " + act.maxDelivery + "    ");

            if(act.stomach==0) {
                this.listofPredators.remove(q);
                q--;
            }
            else{
                if (act.readyToDelivery()) this.listofPredators.add(act.makeChild(this.idCheckTab));

                else if (act.hunger()) {
                    act.searchFood(this.listofPredators, this.listofHerbivores, this.listofPeople);

                    if(act.target.numberOnTheList ==-1){
                        act.moveRandom();
                    }
                    else{
                        act.eatFood();

                        if(act.target.isInRange==1) {
                            this.deleteTarget(act.target);

                            //System.out.println(act.id + " " + act.target.id + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                            if ((act.target.id%100) > 0 && (act.target.id%100) <= 10 && q > act.target.numberOnTheList)
                                q--;
                        }
                    }
                }
                else
                    act.moveRandom();

                if (!act.hunger())
                    act.delivery++;

                act.age++;
                System.out.println(listofPredators.size() + " " + q);
                this.listofPredators.set(q, act);
            }
        }
    }

    void humanActivities (){
        this.statistics[0][currentTurn]=this.listofPeople.size();
        for(int q=0; q<this.listofPeople.size(); q++){
            Human act= (Human) this.listofPeople.get(q);
            act.stomach--;

            if(act.stomach==0) {//nie wiem, czy to jest na pewno dobrze
                this.listofPeople.remove(q);
                q--;
            }
            else{
                if (act.readyToDelivery()) this.listofPeople.add(act.makeChild(this.idCheckTab));

                else if (act.hunger()) {

                    act.searchFood(this.listofFruits, this.listofPredators, this.listofHerbivores);

                    if(act.target.numberOnTheList ==-1){
                        act.moveRandom();
                    }
                    else{
                        if(act.target.isInRange==1) {
                            this.deleteTarget(act.target);
                            act.eatFood();
                        }
                        else act.moveToFood();
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
                this.listofPeople.set(q, act);
            }
        }
    }

    void spawnFruits(){
        int act=quantity;

        if(this.weather==0) act*=0.5;
        else if(this.weather==1) act*=0.75;
        else if(this.weather==3) act*=1.5;
        else if(this.weather==4) return;
        else{
            act*=0.25;
            for(int q=0; q<act; q++){
                if(!this.listofFruits.isEmpty()){
                    this.listofFruits.remove(0);
                }
            }
            return;
        }
        for(int q=0; q<act; q++){
            Random rand = new Random();
            this.listofFruits.add(new Fruit(rand.nextInt(this.sizeX)+1,rand.nextInt(this.sizeY)+1, rand.nextInt(10)+1));
        }
    }
    void deleteTarget(Target target){
        //System.out.print(target.id + " " + idCheckTab[target.id] + " " + target.typeOf + " ");

        if(target.typeOf ==0)
            this.listofFruits.remove(target.numberOnTheList);

        else{
            this.idCheckTab[target.id]=0;
            //System.out.println(idCheckTab[target.id]);

            if(target.typeOf ==1)
                this.listofPredators.remove(target.numberOnTheList);

            else if(target.typeOf ==2)
                this.listofHerbivores.remove(target.numberOnTheList);

            else if(target.typeOf ==3) {
                this.listofPeople.remove(target.numberOnTheList);
            }
        }
    }
    void systemOut(){
        System.out.print("Today is: ");System.out.print(this.currentTurn-100);System.out.print(" turn, weather is: ");
        if(this.weather==0) System.out.print(" cloudy "); else if(this.weather==1) System.out.print(" foggy "); else if(this.weather==2) System.out.print(" clear "); else if(this.weather==3) System.out.print(" sunny "); else if(this.weather==4) System.out.print(" hot "); else if(this.weather==5) System.out.print(" drought ");
        System.out.println();

        System.out.print("Animal type | This turn | Last turn | 10 turns  | 20 turns  | 50 turns  | 100 turns  | At beginning \n");

        for(int q=0; q<=20; q++){
            System.out.print("Animal name | ");
            printout(8, q, 0);
            printout(8, q, 1);
            printout(8, q, 10);
            printout(8, q, 20);
            printout(8, q, 50);
            printout(9, q, 100);

            System.out.print(this.statistics[q][100]);

            System.out.print("\n");
        }
    }
    void printout(int fillUp, int currentAnimal, int actualTurn){
        int divider=1;
        while((this.statistics[currentAnimal][this.currentTurn-actualTurn])/divider>=10){
            divider*=10;
            fillUp--;
        }
        //System.out.println(fillUp + " " + divider);
        System.out.print(this.statistics[currentAnimal][this.currentTurn-actualTurn]); for(int w=0; w<fillUp; w++) System.out.print(" "); System.out.print(" | ");

    }
}

























