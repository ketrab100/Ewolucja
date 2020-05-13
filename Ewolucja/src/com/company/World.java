package com.company;

import java.io.PrintStream;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    int sizeX;
    int sizeY;
    int weather=3;
    int quantity;
    int idCheckTab[]= new int[250000];
    PrintStream outStream;

    List<Predator> listofPredators = new ArrayList<Predator>();
<<<<<<< Updated upstream
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
=======
    List<Animal> listofHerbivores = new ArrayList<Animal>();
    List<Animal> listofPeople = new ArrayList<Animal>();
    List<Fruit> listofFruits = new ArrayList<Fruit>();

    void beginGame(int animalQuantity, int humanQuantity, int humanStrenght){
        addToWorld(5,PRE0.class);

    }

    public  void  addToWorld(int howMuch, Class<? extends Animal> animalClass){
        Animal animal = null;
        try {
            animal = animalClass.newInstance();
            animal.randomIni();
            if (animal instanceof Predator)
                listofPredators.add((Predator)animal);
            if (animal instanceof Herbivore)
                listofHerbivores.add((Herbivore)animal);
            if (animal instanceof Human)
                listofPeople.add((Human)animal);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
>>>>>>> Stashed changes
        }

    }

    public World(int sizeX,int sizeY, PrintStream _outStream){
        this.outStream= _outStream;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.quantity=sizeX/10+sizeY/10;
    }
    void turn(){
        Random rand = new Random();
        weather=rand.nextInt(5);
        //pogoda 0-chmury, słaby wzrost | 1-przelotne chmury, trochę słabszy wzrost | 2-czyste niebo, normalnie
        //3-słoneczny dzień, lepszy wzrost | 4-upał, brak wzrostu | 5-susza, rośliny umierają

        //this.humanActivities();
        //this.herbivoreActivities();
        this.predatorActivities();

    }
<<<<<<< Updated upstream
    void play(){
        while(!listofPeople.isEmpty())
            turn();
=======
    void play() throws InterruptedException {
        while(!this.listofPeople.isEmpty()) {
            this.turn();
            //System.out.print("XD");
        }
        this.systemOut();
        System.out.println(this.listofPeople.size());
        /*for(int q=0; q<102; q++){
            for(int w=0; w<102; w++){
                System.out.print(this.pomocnaukowa[q][w] + " ");
            }
            System.out.println();
        }
        */
>>>>>>> Stashed changes
    }
    void herbivoreActivities (){
        for(int q=0; q<listofHerbivores.size(); q++){
            Herbivore act = listofHerbivores.get(q);
            act.stomach--;

            if(act.stomach==0) {//nie wiem, czy to jest na pewno dobrze
                listofHerbivores.remove(q);
                q--;
            }
            else {
                if (act.readyToDelivery()) act.makeChild( listofHerbivores, idCheckTab);

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

                if (act.hunger() != true)
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
            act.stomach--;

            if(act.stomach==0) {//nie wiem, czy to jest na pewno dobrze
                listofPredators.remove(q);
                q--;
            }
            else{
                if (act.readyToDelivery()){
                    //act.makeChild(listofPredators, idCheckTab);
                    this.listofPredators.add(act.makeChild());
                }

                else if (act.hunger()) {
                    act.searchFood(listofPredators, listofHerbivores, listofPeople);

                    if(act.target.numberOnTheList ==-1){
                        act.moveRandom();
                    }
                    else{
                        act.eatFood();
                        if(act.target.isInRange==1) {
                            this.deleteTarget(act.target);
                            
                            if ((act.id - (act.id / 100) * 100) > 0 && (act.id - (act.id / 100) * 100) <= 10 && q > act.target.numberOnTheList)
                                q--;
                        }
                    }
                }
                else
                    act.moveRandom();

                if (act.hunger() != true)
                    act.delivery++;

                act.age++;
                listofPredators.set(q, act);
            }
        }
    }

    void humanActivities (){
        for(int q=0; q<listofPeople.size(); q++){
            Human act= listofPeople.get(q);

            act.stomach--;

            if(act.stomach==0) {//nie wiem, czy to jest na pewno dobrze
                listofPeople.remove(q);
                q--;
            }
            else{
                if (act.readyToDelivery()) act.makeChild(listofPeople, idCheckTab);

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

                if (act.hunger() != true)
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
            for(int q=act; q<act; q++){
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

}

























