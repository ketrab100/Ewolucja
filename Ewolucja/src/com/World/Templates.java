package com.World;

import com.Lifeforms.Herbivore;
import com.Lifeforms.Human;
import com.Lifeforms.Predator;

public class Templates {
    int humanStrength=5;

    Predator Tiger  = new Predator(15,15,8,30,30,35,32);
    Predator Wolf = new Predator(10,20,10,20,25,21,20);
    Predator Snake = new Predator(2,12,5,12,20,12,12);
    Predator Dog = new Predator(7,15,9,7,12,9,7);
    Predator YourAnimal1;

    Herbivore Cow = new Herbivore(20,20,5,20,50,24);
    Herbivore Goat = new Herbivore(10,20,9,15,15,8);
    Herbivore Sheep = new Herbivore(12,5,3,10,15,6);
    Herbivore Horse = new Herbivore(21,10,20,35,40,30);
    Herbivore YourAnimal2;
    Human Human;

    void createYourAnimal1Template(int _value, int _searchRange, int _speed , int _maxDelivery, int _maxStomach , int _resistance, int _strength ){
        YourAnimal1 = new Predator(_value, _searchRange,_speed,_maxDelivery,_maxStomach,_resistance,_strength);
    }
    void createYourAnimal2Template(int _value, int _searchRange, int _speed , int _maxDelivery, int _maxStomach , int _resistance){
        YourAnimal2 = new Herbivore(_value, _searchRange,_speed,_maxDelivery,_maxStomach,_resistance);
    }
    void createHumanTemplate(){
        Human= new Human (this.humanStrength);
    }
}
