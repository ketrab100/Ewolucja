package com.Lifeforms;

import java.util.Random;

public class Position {
    private int X;
    private int Y;

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
