package com.Lifeforms;

import java.util.List;
import java.util.Random;


public class Animal implements Cloneable {

    protected int strenght;
    protected int id;
    public Position position= new Position();
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

        this.position.changeX((this.position.getX()+help));
        help=this.speed-Math.abs(help);
        if(this.position.getX()<=0){
            help+=Math.abs(this.position.getX())+1;
            this.position.changeX(1);
        }
        else if(this.position.getX()>sizeX){
            help=help-sizeX+this.position.getX();
            this.position.changeX(sizeX);
        }
        this.position.changeY((this.position.getY()+(rand.nextInt(help*2+1)-help)));

        if(this.position.getY()<=0) this.position.changeY(1);
        else if(this.position.getY()>sizeY) this.position.changeY(sizeY);
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
        if (this.target.isInRange == true) {
            this.stomach += this.target.value;
            this.stomach = Math.min(this.stomach, this.maxStomach);
            this.position.changeY(this.target.position.getY());
            this.position.changeX(this.target.position.getX());
        }
    }

    public void moveToFood(){
        int movementLeft = this.speed;

        if(this.target.position.getX()>this.position.getX()){
            movementLeft-=Math.min(movementLeft, Math.abs(this.target.position.getX()-this.position.getX()));
            this.position.changeX((this.position.getX()+this.speed-movementLeft));
        }
        else{
            movementLeft-=Math.min(movementLeft, Math.abs(this.target.position.getX()-this.position.getX()));
            this.position.changeX((this.position.getX()-this.speed+movementLeft));
        }
        if(movementLeft!=0){
            if(this.target.position.getY()>this.position.getY())
                this.position.changeY((this.position.getY()+movementLeft));
            else
                this.position.changeY((this.position.getY()-movementLeft));
        }
    }

    /**
     * Searching whole list of fruits for the best fruit for this animal
     * @param listofFruits
     */
    void searchFruit(List<Fruit> listofFruits){

        for(int q=0; q<listofFruits.size(); q++){
            Fruit food = listofFruits.get(q);
            if(this.checkDistancetoFruit(food)) {
                if (this.canIgetThere(food.position.getX(), food.position.getY())) {
                    if (food.value > this.target.value) {
                        this.target.numberOnTheList = q;
                        this.target.typeOf = 0;
                        this.target.value = food.value;
                        this.target.isInRange = true;
                    }
                }
                else if (this.target.isInRange == false){
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
            this.target.position.changeX(listofFruits.get(this.target.numberOnTheList).position.getX());
            this.target.position.changeY(listofFruits.get(this.target.numberOnTheList).position.getY());
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
                if (this.canIgetThere(prey.position.getX(), prey.position.getY())) {
                    if (prey.value > this.target.value) {
                        this.target.numberOnTheList = q;
                        this.target.typeOf=currentList;
                        this.target.value = prey.value;
                        this.target.id = prey.id;
                        this.target.isInRange = true;
                    }
                }
                else if (this.target.isInRange == false) {
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
            this.target.position.changeX(listofPreys.get(this.target.numberOnTheList).position.getX());
            this.target.position.changeY(listofPreys.get(this.target.numberOnTheList).position.getY());
        }
    }

    boolean checkDistancetoFruit(Fruit food){
        if(Math.abs(food.position.getX()-this.position.getX())+Math.abs(food.position.getY()-this.position.getY())<=this.searchRange) return true;
        return false;
    }

    boolean checkDistancetoAnimal(Animal prey){
        if (this.strenght > prey.resistance) {
            if (Math.abs(prey.position.getX() - this.position.getX()) + Math.abs(prey.position.getY() - this.position.getY()) <= this.searchRange) return true;
        }
        return false;
    }

    boolean canIgetThere(int X, int Y){
        if(Math.abs(X - this.position.getX()) + Math.abs(Y - this.position.getY()) <= this.speed) return true;
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
        if(this.target.isInRange) return true;
        return false;
    }

    public int getnewID(int id){
        this.id=id;
        return this.id;
    }

    public void randomInitialization(int x, int y){
        this.stomach = this.maxStomach;
        Random random = new Random();
        this.position.changeX(random.nextInt(x)+1);
        this.position.changeY(random.nextInt(y)+1);
    }
}
