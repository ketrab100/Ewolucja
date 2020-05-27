package com.company;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    int sizeX=200;
    int sizeY=200;
    int quantity;
    int weather=3;
    int currentTurn=100;
    int aliveAnimals;
    int[] idCheckTab= new int[250000];
    int[][] statistics = new int[50][250000];
    int[] animalQuantity = new int[50];
    String[] animalTypes = new String[50];

    List<Predator> listofPredators = new ArrayList<Predator>();
    List<Herbivore> listofHerbivores = new ArrayList<Herbivore>();
    List<Human> listofPeople = new ArrayList<Human>();
    List<Fruit> listofFruits = new ArrayList<Fruit>();

    /**
     * Preparing lists containing Predators Herbivores People and Fruits
     * @param humanStrength People strength when the simulation starts
     */
    void beginGame(int humanStrength){
        this.quantity=(this.sizeX*this.sizeY)/150;

        this.addToWorld(animalQuantity[1],Tiger.class,1);
        this.addToWorld(animalQuantity[2],Wolf.class,2);
        this.addToWorld(animalQuantity[3],Snake.class, 3);
        this.addToWorld(animalQuantity[4],Dog.class, 4);

        this.addToWorld(animalQuantity[11],Goat.class,11);
        this.addToWorld(animalQuantity[12],Cow.class,12);
        this.addToWorld(animalQuantity[13],Sheep.class, 13);
        this.addToWorld(animalQuantity[14],Horse.class, 14);

        this.statistics[0][100]=animalQuantity[0];
        for(int q=0; q<animalQuantity[0]; q++){
            Human human= new Human();
            human.randomInitialization(this.sizeX, this.sizeY);
            human.strenght=humanStrength;
            human.id=0+q*100;
            this.idCheckTab[0+q*100]=1;
            this.listofPeople.add(human);
        }
        this.spawnFruits();
    }

    public World(){
        this.fillanimalTypes();
        this.animalQuantity[0]=5;

        for(int q=1; q<=10; q++){
            this.animalQuantity[q]=2;
        }
        for(int q=11; q<=20; q++){
            this.animalQuantity[q]=10;
        }
    }

    /**
     * All actions during one day
     *
     */
    void turn() {
        /* //do testowania statystyk
        for(int q=0; q<11; q++){
            for(int w=0; w<10; w++){
                System.out.print(this.statistics[q][w+100]);
            }
            System.out.println();
        }
        for(int q=0; q<250000; q++)
            if(idCheckTab[q]!=0)System.out.print(q + " ");
        */
        //System.out.println(this.listofFruits.size() +  " " + this.listofPeople.size() + " " + this.listofPredators.size() + " " + this.listofHerbivores.size());
        //this.systemOut();
        //System.out.println(listofPredators.get(1).positionX+"   " +listofPredators.get(1).positionY);
        //Thread.sleep(1000);
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
        while(!this.listofPeople.isEmpty() || this.aliveAnimals>0) {
            this.systemOut();
            this.turn();
            Thread.sleep(1000);
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
    }

    void herbivoreActivities (){
        for(int q=0; q<this.listofHerbivores.size(); q++){
            Herbivore act = (Herbivore) this.listofHerbivores.get(q);
            this.statistics[act.id%100][currentTurn]++;
            act.stomach--;

            if(act.stomach==0) {
                this.listofHerbivores.remove(q);
                q--;
            }
            else {
                if (act.readyToDelivery())  this.listofHerbivores.add(act.makeChild(this.idCheckTab));

                else if (act.isHungry()) {
                    act.searchFood(this.listofFruits);

                    if(act.target.numberOnTheList ==-1){
                        act.moveRandom(this.sizeX, this.sizeY);
                    }
                    else{
                        if(act.target.isInRange==1) {
                            //System.out.print("ja jem " + act.id + " ");
                            this.deleteTarget(act.target);
                            act.eatFood();
                            //System.out.print(act.stomach + "\n");
                        }
                        else act.moveToFood();
                    }
                }
                else
                    act.moveRandom(this.sizeX, this.sizeY);

                act.increaseStats();

                this.listofHerbivores.set(q, act);
            }
        }
    }

    void predatorActivities (){
        for (int q=0; q<this.listofPredators.size(); q++){
            Predator act = (Predator) this.listofPredators.get(q);
            this.statistics[act.id%100][currentTurn]++;

            act.stomach--;

            if(act.stomach==0) {
                this.listofPredators.remove(q);
                q--;
            }
            else{
                if (act.
                        readyToDelivery()) this.listofPredators.add(act.makeChild(this.idCheckTab));

                else if (act.isHungry()) {
                    //System.out.print(act.id+ " ");
                    act.searchFood(this.listofPredators, this.listofHerbivores, this.listofPeople);

                    if(act.target.numberOnTheList ==-1){
                        act.moveRandom(this.sizeX, this.sizeY);
                    }
                    else{
                        if(act.target.isInRange==1) {
                            //System.out.print("ja jem " + act.target.id + " ");
                            this.deleteTarget(act.target);
                            act.eatFood();
                            //System.out.print(act.stomach);

                            if ((act.target.id%100) > 0 && (act.target.id%100) <= 10 && q > act.target.numberOnTheList)
                                q--;
                        }
                        else act.moveToFood();
                    }
                    //System.out.println();
                }
                else
                    act.moveRandom(this.sizeX, this.sizeY);

                act.increaseStats();

                this.listofPredators.set(q, act);
            }
        }
    }

    void humanActivities (){
        this.statistics[0][currentTurn]=this.listofPeople.size();
        for(int q=0; q<this.listofPeople.size(); q++){
            Human act= null;
            act =this.listofPeople.get(q);
            //System.out.println(this.listofPeople.get(q).age + " " + act.age + " " + q);
            act.stomach--;

            if(act.stomach==0) {
                this.listofPeople.remove(q);
                q--;
            }
            else{
                if (act.readyToDelivery()) this.listofPeople.add(act.makeChild(this.idCheckTab));

                else if (act.isHungry()) {
                    act.searchFood(this.listofFruits, this.listofPredators, this.listofHerbivores);

                    if(act.target.numberOnTheList ==-1){
                        act.moveRandom(this.sizeX, this.sizeY);
                    }
                    else{
                        if(act.target.isInRange==1) {
                            //System.out.print("ja jem " + act.id + " ");
                            this.deleteTarget(act.target);
                            act.eatFood();
                            //System.out.print(act.stomach + "\n");
                        }
                        else act.moveToFood();
                    }
                }
                else
                    act.moveRandom(this.sizeX, this.sizeY);

                act.increaseStats();

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
            //this.pomocnaukowa[listofFruits.get(listofFruits.size()-1).positionX][listofFruits.get(listofFruits.size()-1).positionY]++;
        }

    }

    /**
     * Deleting target from right list
     * @param target
     */
    void deleteTarget(Target target){

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

    /**
     * Writing simulation summary
     */
    void systemOut(){
        System.out.print(" Today is: ");System.out.print(this.currentTurn-100);System.out.print(" turn, weather is:");
        if(this.weather==0) System.out.print(" cloudy "); else if(this.weather==1) System.out.print(" foggy "); else if(this.weather==2) System.out.print(" clear "); else if(this.weather==3) System.out.print(" sunny "); else if(this.weather==4) System.out.print(" hot "); else if(this.weather==5) System.out.print(" drought ");

        if(!listofPeople.isEmpty())
            System.out.print("Strenght of oldest Human is: " + listofPeople.get(0).strenght);

        System.out.println();

        System.out.print(" Animal type | This turn | Last turn | 10 turns  | 20 turns  | 50 turns  | 100 turns  | At beginning \n");

        this.aliveAnimals=0-this.statistics[0][this.currentTurn];

        for(int q=0; q<=20; q++) {
            if (this.statistics[q][100] != 0) {
                this.aliveAnimals=this.aliveAnimals-this.statistics[q][this.currentTurn];

                System.out.print(animalTypes[q] + " | ");
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

    /**
     * Adding animals to right list
     * @param howMuch
     * @param animalClass
     * @param idStartNumber
     */
    public  void  addToWorld(int howMuch, Class<? extends Animal> animalClass,int idStartNumber) {

        for (int i = 0; i < howMuch; i++) {
            this.statistics[idStartNumber][100]=howMuch;
            Animal animal = null;
            try {
                animal = animalClass.newInstance();
                animal.randomInitialization(this.sizeX,this.sizeY);
                if (animal instanceof Predator) {
                    animal.id = i * 100+idStartNumber;
                    listofPredators.add((Predator) animal);
                    this.idCheckTab[idStartNumber+i*100]=1;
                }
                if (animal instanceof Herbivore) {
                    animal.id = i * 100+idStartNumber;
                    listofHerbivores.add((Herbivore) animal);
                    this.idCheckTab[idStartNumber+i*100]=1;
                }
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    void fillanimalTypes(){
        this.animalTypes[0]=" Human      ";
        this.animalTypes[1]=" Tiger      ";
        this.animalTypes[2]=" Wolf       ";
        this.animalTypes[3]=" Snake      ";
        this.animalTypes[4]=" Dog        ";
        this.animalTypes[5]=" Human      ";
        this.animalTypes[6]=" Human      ";
        this.animalTypes[7]=" Human      ";
        this.animalTypes[8]=" Human      ";
        this.animalTypes[9]=" Human      ";
        this.animalTypes[10]=" YourAnimal1";
        this.animalTypes[11]=" Goat       ";
        this.animalTypes[12]=" Cow        ";
        this.animalTypes[13]=" Sheep      ";
        this.animalTypes[14]=" Horse      ";
        this.animalTypes[15]=" Human      ";
        this.animalTypes[16]=" Human      ";
        this.animalTypes[17]=" Human      ";
        this.animalTypes[18]=" Human      ";
        this.animalTypes[19]=" Human      ";
        this.animalTypes[20]=" YourAnimal2";
    }
}

























