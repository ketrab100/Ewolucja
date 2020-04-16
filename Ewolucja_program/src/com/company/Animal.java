package com.company;

import java.util.Random;

public class Animal {
    int positonX;
    int positonY;
    int speed;

    void move (){
        Random r = new Random();
        if (Math.random()>0.5) {
            this.positonX+=r.nextInt(speed);
        }
        if (Math.random()>0.5) {
            this.positonY+=r.nextInt(speed);
        }
        if (Math.random()<0.5) {
            this.positonX-=r.nextInt(speed);
        }
        if (Math.random()<0.5) {
            this.positonY-=r.nextInt(speed);
        }


    }
}
