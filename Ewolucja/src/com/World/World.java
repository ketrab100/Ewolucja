package com.World;

import com.Lifeforms.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class World {
    private int sizeX=200;
    private int sizeY=200;
    private int quantity;
    private int weather=3;
    private int currentTurn=0;

    private int[] idCheckTab= new int[2500000]; //zmienić na jakiś vector czy coś
    protected Statistics statistics = new Statistics();
    protected Templates templates = new Templates();
    protected String[] animalTypes = new String[50];

    private List<Predator> listofPredators = new ArrayList<Predator>();
    private List<Herbivore> listofHerbivores = new ArrayList<Herbivore>();
    private List<Human> listofPeople = new ArrayList<Human>();
    private List<Fruit> listofFruits = new ArrayList<Fruit>();
    /**
     * Preparing lists containing Predators Herbivores People and Fruits
     */
    void beginGame(){
        this.quantity=(this.sizeY*this.sizeX)/150;

        this.addPredatorToWorld(this.templates.givePredator(1),1);
        this.addPredatorToWorld(this.templates.givePredator(2),2);
        this.addPredatorToWorld(this.templates.givePredator(3),3);
        this.addPredatorToWorld(this.templates.givePredator(4),4);
        this.addPredatorToWorld(this.templates.givePredator(5), 10);

        this.addHerbivoreToWorld(this.templates.giveHerbivore(1),11);
        this.addHerbivoreToWorld(this.templates.giveHerbivore(2),12);
        this.addHerbivoreToWorld(this.templates.giveHerbivore(3),13);
        this.addHerbivoreToWorld(this.templates.giveHerbivore(4),14);
        this.addHerbivoreToWorld(this.templates.giveHerbivore(5), 20);

        this.templates.createHumanTemplate();
        this.addPeopleToWorld(this.templates.giveHuman());

        this.spawnFruits();
    }

    public World(){
        this.fillanimalTypes();

    }

    /**
     * All actions during one day
     *
     */
    void turn() {
        this.currentTurn++;
        this.statistics.turnPassed();
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

        while(!this.listofPeople.isEmpty() && (this.listofHerbivores.size()+this.listofPredators.size())>0) {
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
            this.statistics.countMeAs(act.animalTypeID());
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
            this.statistics.countMeAs(act.animalTypeID());
            act.lowerStomach();

            //System.out.print(act.id + "   ");
            //if(act.id==4) System.out.println(act.position.getX() + " " + act.position.getY() + " " + act.speed);

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
                            //System.out.print("ja jem ");
                            this.deleteTarget(act.target);
                            act.eatFood();
                            //System.out.print(act.stomach + " ");
                            if (act.haveItMovedThisTurn(q))
                                q--;
                        }
                        else act.moveToFood();
                    }
                    else{
                        act.moveRandom(this.sizeX, this.sizeY);
                    }
                    //System.out.println(act.target.myID());
                }
                else
                    act.moveRandom(this.sizeX, this.sizeY);

                act.increaseStats();
                this.listofPredators.set(q, act);
            }
        }
    }

    /**
     * Each action done by people
     */
    void humanActivities (){

        for(int q=0; q<this.listofPeople.size(); q++){
            this.statistics.countMeAs(0);
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
            //this.statistics.IwasEaten(target.targetTypeID());

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
        System.out.print(" Today is: ");System.out.print(this.currentTurn);System.out.print(" turn, weather is:");
        if(this.weather==0) System.out.print(" cloudy "); else if(this.weather==1) System.out.print(" foggy "); else if(this.weather==2) System.out.print(" clear "); else if(this.weather==3) System.out.print(" sunny "); else if(this.weather==4) System.out.print(" hot "); else if(this.weather==5) System.out.print(" drought ");

        if(!listofPeople.isEmpty())
            System.out.print("Strenght of oldest Human is: " + listofPeople.get(0).checkmyStrenght());

        System.out.println();

        System.out.print(" Animal type | This turn | Last turn | 10 turns  | 20 turns  | 50 turns  | 100 turns  | At beginning \n");

        for(int q=0; q<=20; q++) {
            if (this.templates.howMuchAnimals(q) != 0) {
                System.out.print(animalTypes[q] + " | ");
                printout(8, q, 0);
                printout(8, q, 1);
                printout(8, q, 10);
                printout(8, q, 20);
                printout(8, q, 50);
                printout(9, q, 100);

                System.out.print(this.templates.howMuchAnimals(q));

                System.out.print("\n");
            }
        }
    }

    void printout(int fillUp, int currentAnimalType, int actualTurn){
        int divider=1;
        while((this.statistics.giveMyStats(currentAnimalType, actualTurn))/divider>=10){
            divider*=10;
            fillUp--;
        }
        //System.out.println(fillUp + " " + divider);
        System.out.print(this.statistics.giveMyStats(currentAnimalType, actualTurn)); for(int w=0; w<fillUp; w++) System.out.print(" "); System.out.print(" | ");

    }

    /**
     * Copy predator from templates, give right ID and add to the list
     * @param predator
     * @param idStartNumber
     */
    public  void  addPredatorToWorld(Predator predator,int idStartNumber) {

        for(int q=0; q<this.templates.howMuchAnimals(idStartNumber); q++){
            this.statistics.countMeAs(idStartNumber);
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

        for(int q=0; q<this.templates.howMuchAnimals(idStartNumber); q++){
            this.statistics.countMeAs(idStartNumber);
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

        for(int q=0; q<this.templates.howMuchAnimals(0); q++){
            this.statistics.countMeAs(0);
            Human newHuman = (Human) human.clone();
            newHuman.randomInitialization(this.sizeX, this.sizeY);
            this.idCheckTab[newHuman.getnewID(0+q*100)]=1;
            this.listofPeople.add(newHuman);
        }
    }

    void changeWorldSize(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("X size: ");
        this.sizeX = scanner.nextInt();
        System.out.printf("Y size: ");
        this.sizeY = scanner.nextInt();
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


















