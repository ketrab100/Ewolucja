package com.Lifeforms;

import java.util.List;
import java.util.Random;


public class Animal implements Cloneable {

    protected int strenght;
    protected int id;
    protected int positionX;
    protected int positionY;
    protected int age=0;
    protected int stomach;
    protected int delivery;
    protected int value;
    protected int speed;
    protected int maxStomach;
    protected int maxDelivery;
    protected int searchRange;
    protected int resistance;
    public Target target = new Target();

    public Animal(){
    }

    /**
     * Setting random position
     * @param x
     * @param y
     */
    public void randomInitialization(int x, int y){
        this.stomach = this.maxStomach;
        Random random = new Random();
        this.positionX=random.nextInt(x)+1;
        this.positionY=random.nextInt(y)+1;
    }

    public Object clone() {
        Object newObject=null;
        try { newObject = super.clone(); } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newObject;
    }

    /**
     * Random movement depending on speed
     * @param sizeX
     * @param sizeY
     */
    public void moveRandom (int sizeX, int sizeY){
        Random rand=new Random();

        int help = (rand.nextInt(this.speed*2+1)-this.speed);
        this.positionX+=help;
        help=this.speed-Math.abs(help);
        if(this.positionX<=0){
            help+=Math.abs(this.positionX)+1;
            this.positionX=1;
        }
        else if(this.positionX>sizeX){
            help=help-sizeX+this.positionX;
            this.positionX=sizeX;
        }
        this.positionY+=(rand.nextInt(help*2+1)-help);

        if(this.positionY<=0) this.positionY=1;
        else if(this.positionY>sizeY) this.positionY=sizeY;
    }

    public boolean readyToDelivery(){
        if (this.delivery>=this.maxDelivery && this.stomach>=3){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isHungry(){
        if(this.stomach<=this.maxStomach/2)
            return true;
        else
            return false;
    }

    public void eatFood() {
        if (this.target.isInRange == 1) {
            this.stomach += this.target.value;
            this.stomach = Math.min(this.stomach, this.maxStomach);
            this.positionY = this.target.positionY;
            this.positionX = this.target.positionX;
        }
    }

    public void moveToFood(){
        int movementLeft = this.speed;

        if(this.target.positionX>this.positionX){
            movementLeft-=Math.min(movementLeft, Math.abs(this.target.positionX-this.positionX));
            this.positionX+=this.speed-movementLeft;
        }
        else{
            this.target.isInRange-=Math.min(movementLeft, Math.abs(this.target.positionX-this.positionX));
            this.positionX-=this.speed+movementLeft;
        }
        if(movementLeft!=0){
            if(this.target.positionY>this.positionY)
                this.positionY+=movementLeft;
            else
                this.positionY-=movementLeft;
        }
    }

    /**
     * Searching whole list of fruits for the best fruit for this animal
     * @param listofFruits
     */
    void searchFruit(List<Fruit> listofFruits){
        /*
        this.target.numberOnTheList=-1;
        this.target.typeOf=-1;
        this.target.isInRange=0;
        this.target.value=0;
         */
        for(int q=0; q<listofFruits.size(); q++){
            Fruit food = listofFruits.get(q);
            if(this.checkDistancetoFruit(food)) {
                if (this.canIgetThere(food.positionX, food.positionY)) {
                    if (food.value > this.target.value) {
                        this.target.numberOnTheList = q;
                        this.target.typeOf = 0;
                        this.target.value = food.value;
                        this.target.isInRange = 1;
                    }
                }
                else if (this.target.isInRange == 0){
                    if (food.value > this.target.value) {
                        this.target.numberOnTheList = q;
                        this.target.typeOf = 0;
                        this.target.value = food.value;
                    }
                }
            }
        }
        if(this.target.typeOf==0) {
            this.target.id=99;
            this.target.positionX = listofFruits.get(this.target.numberOnTheList).positionX;
            this.target.positionY = listofFruits.get(this.target.numberOnTheList).positionY;
        }
    }

    /**
     * Searching whole list of preys for the best food for this animal
     * @param listofPreys
     * @param currentList
     */
    void searchPrey(List<? extends Animal> listofPreys, int currentList) {

        for (int q = 0; q < listofPreys.size(); q++) {
            Animal prey = listofPreys.get(q);

            if(this.checkDistancetoAnimal(prey)){
                if (canIgetThere(prey.positionX, prey.positionY)) {
                    if (prey.value > this.target.value) {
                        this.target.numberOnTheList = q;
                        this.target.typeOf=currentList;
                        this.target.value = prey.value;
                        this.target.id = prey.id;
                        this.target.isInRange = 1;
                    }
                }
                else if (this.target.isInRange == 0) {
                    if (prey.value > this.target.value) {
                        this.target.numberOnTheList = q;
                        this.target.typeOf=currentList;
                        this.target.value = prey.value;
                        this.target.id = prey.id;
                    }
                }
            }
        }
        if (this.target.typeOf==currentList) {
            this.target.positionX = listofPreys.get(this.target.numberOnTheList).positionX;
            this.target.positionY = listofPreys.get(this.target.numberOnTheList).positionY;
        }
    }

    boolean checkDistancetoFruit(Fruit food){
        if(Math.abs(food.positionX-this.positionX)+Math.abs(food.positionY-this.positionY)<=this.searchRange) return true;
        return false;
    }

    boolean checkDistancetoAnimal(Animal prey){
        if (this.strenght > prey.resistance) {
            if (Math.abs(prey.positionX - this.positionX) + Math.abs(prey.positionY - this.positionY) <= this.searchRange) return true;
        }
        return false;
    }

    boolean canIgetThere(int X, int Y){
        if(Math.abs(X - this.positionX) + Math.abs(Y - this.positionY) <= this.speed) return true;
        return false;
    }

    public void lowerStomach(){
        this.stomach--;
    }

    public int animalTypeID(){
        return this.id%100;
    }

    public boolean amIDead(){
        if(this.stomach==0) return true;
        return false;
    }

    public int checkmyStrenght(){
        return this.strenght;
    }

    public boolean canIreachIt(){
        if(this.target.isInRange==1) return true;
        return false;
    }

    public int getnewID(int id){
        this.id=id;
        return this.id;
    }
}
