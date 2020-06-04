package com.World;

import com.Lifeforms.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    protected int sizeX=200;
    protected int sizeY=200;
    protected int quantity;
    protected int weather=3;
    protected int currentTurn=100;
    protected int aliveAnimals;
    protected int[] idCheckTab= new int[2500000];
    protected int[][] statistics = new int[50][2500000];
    protected int[] animalQuantity = new int[50];
    protected Templates templates = new Templates();
    protected String[] animalTypes = new String[50];

    protected List<Predator> listofPredators = new ArrayList<Predator>();
    protected List<Herbivore> listofHerbivores = new ArrayList<Herbivore>();
    protected List<Human> listofPeople = new ArrayList<Human>();
    protected List<Fruit> listofFruits = new ArrayList<Fruit>();
    /**
     * Preparing lists containing Predators Herbivores People and Fruits
     */
    void beginGame(){
        this.quantity=(this.sizeY*this.sizeX)/150;

        this.addPredatorToWorld(this.templates.Tiger,1);
        this.addPredatorToWorld(this.templates.Wolf,2);
        this.addPredatorToWorld(this.templates.Snake,3);
        this.addPredatorToWorld(this.templates.Dog,4);
        this.addPredatorToWorld(this.templates.YourAnimal1, 10);

        this.addHerbivoreToWorld(this.templates.Goat,11);
        this.addHerbivoreToWorld(this.templates.Cow,12);
        this.addHerbivoreToWorld(this.templates.Sheep,13);
        this.addHerbivoreToWorld(this.templates.Horse,14);
        this.addHerbivoreToWorld(this.templates.YourAnimal2, 20);

        this.templates.createHumanTemplate();
        this.addPeopleToWorld(this.templates.Human);

        this.spawnFruits();
    }

    public World(){
        this.fillanimalTypes();
        this.animalQuantity[0]=6;

        for(int q=1; q<=9; q++){
            this.animalQuantity[q]=3;
        }
        for(int q=11; q<=19; q++){
            this.animalQuantity[q]=10;
        }
        this.animalQuantity[3]=5;
        this.animalQuantity[4]=8;
        this.animalQuantity[13]=20;
    }

    /**
     * All actions during one day
     *
     */
    void turn() {
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
        this.aliveAnimals=100;
        while(!this.listofPeople.isEmpty() && this.aliveAnimals>0) {
            this.systemOut();
            this.turn();
            Thread.sleep(1000);
            //System.out.print("XD");
        }
        if(this.listofPeople.size()==0){
            this.systemOut();
            turn();
            this.systemOut();
            System.out.println("People are gone, program will close after you press any button");
        }
        else{
            System.out.println("People are only race left, program will close after you press any button");
        }
    }

    /**
     * Each action done by every herbivore
     */
    void herbivoreActivities (){
        for(int q=0; q<this.listofHerbivores.size(); q++){
            Herbivore act = (Herbivore) this.listofHerbivores.get(q);
            this.statistics[act.animalTypeID()][currentTurn]++;
            act.lowerStomach();

            //System.out.println(act.value + " "+ act.stomach +" "+ act.maxStomach +" "+ act.id);
            if(act.amIDead()) {
                this.listofHerbivores.remove(q);
                q--;
            }
            else {
                if (act.readyToDelivery())  this.listofHerbivores.add(act.makeChild(this.idCheckTab));

                else if (act.isHungry()) {
                    act.searchFood(this.listofFruits);

                    if(act.target.myNumberonList()>=0){
                        if(act.canIreachIt()) {
                            //System.out.print("ja jem " + act.id + " ");
                            this.deleteTarget(act.target);
                            act.eatFood();
                            //System.out.print(act.stomach + "\n");
                        }
                        else act.moveToFood();
                    }
                    else{
                        act.moveRandom(this.sizeX, this.sizeY);
                    }
                }
                else
                    act.moveRandom(this.sizeX, this.sizeY);

                act.increaseStats();

                this.listofHerbivores.set(q, act);
            }
        }
    }

    /**
     * Each action done by every predators
     */
    void predatorActivities (){
        for (int q=0; q<this.listofPredators.size(); q++){
            Predator act = (Predator) this.listofPredators.get(q);
            this.statistics[act.animalTypeID()][currentTurn]++;
            act.lowerStomach();

            if(act.amIDead()) {
                this.listofPredators.remove(q);
                q--;
            }

            else{
                if (act.readyToDelivery()) this.listofPredators.add(act.makeChild(this.idCheckTab));

                else if (act.isHungry()) {
                    //System.out.print(act.id+ " ");
                    act.searchFood(this.listofPredators, this.listofHerbivores, this.listofPeople);

                    if(act.target.myNumberonList()>=0){
                        if(act.canIreachIt()) {
                            //System.out.print("ja jem " + act.id + " ");
                            this.deleteTarget(act.target);
                            act.eatFood();
                            //System.out.print(act.stomach + "\n");
                            if (act.haveItMovedThisTurn(q))
                                q--;
                        }
                        else act.moveToFood();
                    }
                    else{
                        act.moveRandom(this.sizeX, this.sizeY);
                    }
                }
                else
                    act.moveRandom(this.sizeX, this.sizeY);

                act.increaseStats();
                //System.out.println(this.listofPredators.size() +" "+ q);
                this.listofPredators.set(q, act);
            }
        }
    }

    /**
     * Each action done by people
     */
    void humanActivities (){
        this.statistics[0][currentTurn]=this.listofPeople.size();
        for(int q=0; q<this.listofPeople.size(); q++){
            Human act= null;
            act = this.listofPeople.get(q);
            //System.out.println(this.listofPeople.get(q).age + " " + act.age + " " + q);
            act.lowerStomach();

            if(act.amIDead()) {
                this.listofPeople.remove(q);
                q--;
            }
            else{
                if (act.readyToDelivery()) this.listofPeople.add(act.makeChild(this.idCheckTab));

                else if (act.isHungry()) {
                    act.searchFood(this.listofFruits, this.listofPredators, this.listofHerbivores);

                    if(act.target.myNumberonList()>=0){
                        if(act.canIreachIt()) {
                            //System.out.print("ja jem " + act.id + " ");
                            this.deleteTarget(act.target);
                            act.eatFood();
                            //System.out.print(act.stomach + "\n");
                        }
                        else act.moveToFood();
                    }
                    else{
                        act.moveRandom(this.sizeX, this.sizeY);
                    }
                }
                else
                    act.moveRandom(this.sizeX, this.sizeY);

                act.increaseStats();

                this.listofPeople.set(q, act);
            }
        }
    }

    /**
     * Spawning fruits in the world
     */
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

        if(target.myTypeIs() ==0)
            this.listofFruits.remove(target.myNumberonList());

        else{
            this.idCheckTab[target.myID()]=0;
            //System.out.println(idCheckTab[target.id]);

            if(target.myTypeIs() ==1)
                this.listofPredators.remove(target.myNumberonList());

            else if(target.myTypeIs() ==2)
                this.listofHerbivores.remove(target.myNumberonList());

            else if(target.myTypeIs() ==3) {
                this.listofPeople.remove(target.myNumberonList());
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
            System.out.print("Strenght of oldest Human is: " + listofPeople.get(0).checkmyStrenght());

        System.out.println();

        System.out.print(" Animal type | This turn | Last turn | 10 turns  | 20 turns  | 50 turns  | 100 turns  | At beginning \n");

        this.aliveAnimals=0-this.statistics[0][this.currentTurn];

        for(int q=0; q<=20; q++) {
            if (this.statistics[q][100] != 0) {
                this.aliveAnimals=this.aliveAnimals+this.statistics[q][this.currentTurn];
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
     * Copy predator from templates, give right ID and add to the list
     * @param predator
     * @param idStartNumber
     */
    public  void  addPredatorToWorld(Predator predator,int idStartNumber) {
        this.statistics[idStartNumber][100]=this.animalQuantity[idStartNumber];
        for(int q=0; q<this.animalQuantity[idStartNumber]; q++){
            Predator animal = (Predator) predator.clone();
            animal.randomInitialization(this.sizeX, this.sizeY);

            this.idCheckTab[animal.getnewID(idStartNumber+q*100)]=1;
            this.listofPredators.add(animal);
        }
    }

    /**
     * Copy herbivore from templates, give right ID and add to the list
     * @param herbivore
     * @param idStartNumber
     */
    public  void  addHerbivoreToWorld(Herbivore herbivore,int idStartNumber) {
        this.statistics[idStartNumber][100]=this.animalQuantity[idStartNumber];
        for(int q=0; q<this.animalQuantity[idStartNumber]; q++){
            Herbivore animal = (Herbivore) herbivore.clone();
            animal.randomInitialization(this.sizeX, this.sizeY);
            this.idCheckTab[animal.getnewID(idStartNumber+q*100)]=1;
            this.listofHerbivores.add(animal);
        }
    }

    /**
     * Copy human from templates, give right ID and add to the list
     * @param human
     */
    public void addPeopleToWorld(Human human){
        this.statistics[0][100]=animalQuantity[0];
        for(int q=0; q<animalQuantity[0]; q++){
            Human newHuman = (Human) human.clone();
            newHuman.randomInitialization(this.sizeX, this.sizeY);
            this.idCheckTab[newHuman.getnewID(0+q*100)]=1;
            this.listofPeople.add(newHuman);
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


















