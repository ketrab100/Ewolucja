package com.World;

import com.Lifeforms.Herbivore;
import com.Lifeforms.Human;
import com.Lifeforms.Predator;

import java.util.ArrayList;
import java.util.List;

public class Templates {

    private int humanStrength=5;
    private int[] animalQuantity = {6,    3, 5, 6, 9, 0, 0, 0, 0, 0, 0,    20, 20, 30, 10, 0, 0, 0, 0, 0, 0,    0,0,0,0,0};


    private Predator Tiger  = new Predator(15,22,8,30,30,35,32);
    private Predator Wolf = new Predator(10,30,10,20,25,21,20);
    private Predator Snake = new Predator(2,18,5,12,20,12,12);
    private Predator Dog = new Predator(7,20,9,7,12,9,7);
    private Predator YourAnimal1 = new Predator(7,15,9,7,12,9,7);
    private Predator[] PredatorTab = new Predator[]{Tiger, Wolf, Snake, Dog, YourAnimal1};

    private Herbivore Goat = new Herbivore(10,20,9,15,15,8);
    private Herbivore Cow = new Herbivore(20,20,5,20,50,24);
    private Herbivore Sheep = new Herbivore(12,5,3,10,15,6);
    private Herbivore Horse = new Herbivore(21,10,20,35,40,30);
    private Herbivore YourAnimal2 = new Herbivore(12,5,3,10,15,6);
    private Herbivore[] HerbivoreTab = new Herbivore[]{Goat, Cow, Sheep, Horse, YourAnimal2};

    private Human Human;

    public Templates(){
    }

    /**
     * Creating new predator type whit parameters
     * @param _value
     * @param _searchRange
     * @param _speed
     * @param _maxDelivery
     * @param _maxStomach
     * @param _resistance
     * @param _strength
     */
    void createYourAnimal1Template(int _value, int _searchRange, int _speed , int _maxDelivery, int _maxStomach , int _resistance, int _strength ){
        this.YourAnimal1 = new Predator(_value, _searchRange,_speed,_maxDelivery,_maxStomach,_resistance,_strength);
        this.PredatorTab[4]=this.YourAnimal1;
    }

    /**
     * Creating new herbivore type with parameters
     * @param _value
     * @param _searchRange
     * @param _speed
     * @param _maxDelivery
     * @param _maxStomach
     * @param _resistance
     */
    void createYourAnimal2Template(int _value, int _searchRange, int _speed , int _maxDelivery, int _maxStomach , int _resistance){
        this.YourAnimal2 = new Herbivore(_value, _searchRange,_speed,_maxDelivery,_maxStomach,_resistance);
        this.HerbivoreTab[4]=this.YourAnimal2;
    }
    void createHumanTemplate(){
        Human= new Human (this.humanStrength);
    }

    Predator givePredator(int TypeID){
        TypeID--;
        return this.PredatorTab[TypeID];
    }

    Herbivore giveHerbivore(int TypeID){
        TypeID--;
        return this.HerbivoreTab[TypeID];
    }

    Human giveHuman(){return this.Human;}

    void changeHumanStrenght(int newHumanStrenght){this.humanStrength=newHumanStrenght;}

    int howMuchAnimals(int ID){
        return this.animalQuantity[ID];
    }

    void changeAnimalQuantity(int ID, int quantity){
        this.animalQuantity[ID]=quantity;
    }
}
