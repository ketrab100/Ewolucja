package com.Lifeforms;

import java.util.Random;

public class Position {
    private int X;
    private int Y;

    public Position(int x, int y){
        Random rand = new Random();
        X=rand.nextInt(x)+1;
        Y=rand.nextInt(y)+1;
    }

    int getX(){
        return this.X;
    }

    int getY(){
        return this.Y;
    }

    void changeX(int newX){
        this.X=newX;
    }

    void changeY(int newY){
        this.Y=newY;
    }
}
