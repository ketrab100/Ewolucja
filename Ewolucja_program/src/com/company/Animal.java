package com.company;

public class Animal {
    int positonX;
    int positonY;
    int speed;

    void move (){
        this.positonX+=(Math.random()*100)%this.speed;
        this.positonY+=(Math.random()*100)%this.speed;
    }
}
